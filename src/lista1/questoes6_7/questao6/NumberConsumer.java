package lista1.questoes6_7.questao6;

import lista1.questoes6_7.channel.Channel;
import lista1.utils.Utils;

public class NumberConsumer implements Runnable {

    private final Channel<Integer> channel;

    public NumberConsumer(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        while(!this.channel.isClosed()) {
            try {
                Integer number = channel.take();
                if(number != null && Utils.isEven(number)) {
                    System.out.println("Get even number: " + number);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}