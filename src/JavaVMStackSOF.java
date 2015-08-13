/**
 * 
 * -Xss128k
 * 
 * @author bjwshuai
 *
 */
public class JavaVMStackSOF {

	
	
	private int stackLength = 1;
	
	public void stackLeak(){
		
		stackLength++;
		stackLeak();
	}
	
	
	public static void main(String[] args) throws Throwable{
		
		
		JavaVMStackSOF j = new JavaVMStackSOF();
		try {
			j.stackLeak();
			
		} catch (Exception e) {
			System.out.println("---------------"+j.stackLength);
			throw e;
		}
		
		
	}
	
	
}
