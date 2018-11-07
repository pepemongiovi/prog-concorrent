package lista1.questao7;

import lista1.channel.Channel;

public class ConsumerFilterString implements Runnable {

    private final Channel<String> channel;

    public ConsumerFilterString(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        while(true) {
            String alpha = this.channel.takeMessage();
            System.out.println("Filtered string:  " + alpha);
        }
    }
}