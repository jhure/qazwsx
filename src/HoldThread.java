
 class HoldThread extends Thread {  
		 Pojo cdl = new Pojo();  
		  
		    public HoldThread() {  
		        this.setDaemon(true);  
		    }  
		  
		    public void run() {  
		        try {  
		           Thread.sleep(1000000l);
		        } catch (InterruptedException e) {  
		        }  
		    }  
		    
	 } 