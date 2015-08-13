
public class NativeThread {

	
	
	 public static void main(String[] args) {  
		  
	        for (int i = 0;; i++) {  
	            System.out.println("i = " + i);  
	            new Thread(new HoldThread()).start();  
	        }  
	    }  
		    
}
