package lista1.questao1;

import lista1.channel.Channel;

public class Producer implements Runnable{

	Channel channel;
	
	public Producer(Channel channel) {
		this.channel = channel;
	}
	
	@Override
	public void run() {
		for (int i=1; i<11; i++) {
			channel.putMessage("msg " + i);
		}
	}
}