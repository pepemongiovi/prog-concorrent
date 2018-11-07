package lista1.questao6;

import lista1.channel.Channel;
import lista1.utils.Utils;

public class NumberConsumer implements Runnable {

    private final Channel<Integer> channel;

    public NumberConsumer(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        for (int i=0; i < 10; i++) {
            Integer number = channel.takeMessage();
            if(Utils.isEven(number)) {
                System.out.println("Get even number: " + number);
            }
        }
    }
}