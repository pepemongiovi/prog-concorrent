package lista1.questoes6e7.questao7;

import lista1.questoes6e7.channel.Channel;

public class ConsumerFilterString implements Runnable {

    private final Channel<String> channel;

    public ConsumerFilterString(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        while(this.channel.isOpen()) {
            try {
                String alpha = this.channel.take();
                if(alpha != null) {
                    System.out.println("Filtered string:  " + alpha);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}