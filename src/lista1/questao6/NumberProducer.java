package lista1.questao6;

import lista1.channel.Channel;
import lista1.utils.Utils;

import java.util.Random;

public class NumberProducer implements Runnable {

    private final Channel channel;

    public NumberProducer(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        Random random = new Random();
        for(int i=0; i<10; i++) {
            Integer randomNumber = random.nextInt(50);
            this.channel.putMessage(randomNumber);

            // Sleep for 1 second
            Utils.sleep(1000);
        }
    }
}
