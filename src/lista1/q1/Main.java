package lista1.q1;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	public static void main(String args[]) throws InterruptedException {
        
		ChannelImpl channel = new ChannelImpl(5);
		
        Thread t1 = new Thread(new Runnable() 
        { 
            @Override
            public void run() 
            { 
                try {
                	channel.takeMessage();
                    channel.takeMessage();
                    channel.takeMessage();
                	
					Thread.sleep(5000);
					
					channel.takeMessage();
					channel.takeMessage();
					channel.takeMessage();
					channel.takeMessage();
					channel.takeMessage();
					channel.takeMessage();
					channel.takeMessage();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}  
            } 
        }); 
  
        Thread t2 = new Thread(new Runnable() 
        { 
            @Override
            public void run() {     
            	channel.putMessage("msg 1");
            	channel.putMessage("msg 2");
            	channel.putMessage("msg 3");
            	channel.putMessage("msg 4");
            	channel.putMessage("msg 5");
            	channel.putMessage("msg 6");
            	channel.putMessage("msg 7");
            	channel.putMessage("msg 8");
            	channel.putMessage("msg 9");
            	channel.putMessage("msg 10");
            } 
        }); 
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
    }
}

