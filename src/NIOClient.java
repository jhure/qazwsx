

 

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO客户�?
 * @author 小路
 */
public class NIOClient {
	//通道管理�?
	private Selector selector;

	/**
	 * 获得�?��Socket通道，并对该通道做一些初始化的工�?
	 * @param ip 连接的服务器的ip
	 * @param port  连接的服务器的端口号         
	 * @throws IOException
	 */
	public void initClient(String ip,int port) throws IOException {
		// 获得�?��Socket通道
		SocketChannel channel = SocketChannel.open();
		// 设置通道为非阻塞
		channel.configureBlocking(false);
		// 获得�?��通道管理�?
		this.selector = Selector.open();
		
		// 客户端连接服务器,其实方法执行并没有实现连接，�?��在listen（）方法中调
		//用channel.finishConnect();才能完成连接
		channel.connect(new InetSocketAddress(ip,port));
		//将�?道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件�?
		channel.register(selector, SelectionKey.OP_CONNECT);
	}

	/**
	 * 采用轮询的方式监听selector上是否有�?��处理的事件，如果有，则进行处�?
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void listen() throws IOException {
		// 轮询访问selector
		while (true) {
			selector.select();
			// 获得selector中�?中的项的迭代�?
			Iterator ite = this.selector.selectedKeys().iterator();
			while (ite.hasNext()) {
				SelectionKey key = (SelectionKey) ite.next();
				// 删除已�?的key,以防重复处理
				ite.remove();
				// 连接事件发生
				if (key.isConnectable()) {
					SocketChannel channel = (SocketChannel) key
							.channel();
					// 如果正在连接，则完成连接
					if(channel.isConnectionPending()){
						channel.finishConnect();
						
					}
					// 设置成非阻塞
					channel.configureBlocking(false);

					//在这里可以给服务端发送信息哦
					channel.write(ByteBuffer.wrap(new String("").getBytes()));
					//在和服务端连接成功之后，为了可以接收到服务端的信息，�?��给�?道设置读的权限�?
					channel.register(this.selector, SelectionKey.OP_READ);
					
					// 获得了可读的事件
				} else if (key.isReadable()) {
						read(key);
				}

			}

		}
	}
	/**
	 * 处理读取服务端发来的信息 的事�?
	 * @param key
	 * @throws IOException 
	 */
	public void read(SelectionKey key) throws IOException{
		//和服务端的read方法�?��
	}
	
	
	/**
	 * 启动客户端测�?
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		NIOClient client = new NIOClient();
		client.initClient("localhost",8000);
		client.listen();
	}

}
