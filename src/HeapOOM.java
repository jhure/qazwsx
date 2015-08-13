import java.util.Vector;

/**
 * 
 * VM Args  -Xms 20m -Xmx 20m -XX: +HeapDumpOnOutOfMemoryError
 * 
 * 
 * @author bjwshuai
 *
 */
public class HeapOOM {

	
	
	static class OOMObject{}
	
	
	public static void main(String[] args) {
		
		
		/*List<OOMObject> list = new ArrayList<OOMObject>();
		
		
		while(true){
			list.add(new OOMObject());
		}*/
		
		
		Vector v=new Vector(10);   
		for (int i=1;i<100; i++) {   
		    Object o=new Object();   
		    v.add(o);   
		    o=null;   
		}  
		
		
		
	}
	
}
