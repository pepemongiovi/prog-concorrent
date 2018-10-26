package lista1.q1;

public class Producer implements Runnable{

	Channel channel;
	
	public Producer(Channel channel) {
		this.channel = channel;
	}
	
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

}
