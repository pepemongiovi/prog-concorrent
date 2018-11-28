package lista1.questao1;

import lista1.utils.Utils;

public class Consumer implements Runnable{
	
	Channel channel;
	
	public Consumer(Channel channel) {
		this.channel = channel;
	}
	
    /**
     * Gets messages from the channel
     */
	@Override
	public void run() {
		for(int i=0; i < 3; i++) {
			System.err.println("MSG READ: " + channel.takeMessage());
		}

		// Sleep for 5 seconds
		Utils.sleep(5000);

		for(int i=0; i < 7; i++) {
			System.err.println("MSG READ: " + channel.takeMessage());
		}
	}
}