package lista1.q1;

public class Consumer implements Runnable{
	
	Channel channel;
	
	public Consumer(Channel channel) {
		this.channel = channel;
	}
	
	@Override
	public void run() {
		channel.takeMessage();
        channel.takeMessage();
        channel.takeMessage();
    	
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		channel.takeMessage();
		channel.takeMessage();
		channel.takeMessage();
		channel.takeMessage();
		channel.takeMessage();
		channel.takeMessage();
		channel.takeMessage();
	}

}
