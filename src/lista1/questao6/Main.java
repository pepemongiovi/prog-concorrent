package lista1.questao6;

import lista1.channel.Channel;
import lista1.channel.ChannelImpl;

public class Main {

    public static void main(String args[]) throws InterruptedException {
        Channel<Integer> channel = new ChannelImpl<>();

        NumberProducer numberProducer = new NumberProducer(channel);
        NumberConsumer numberConsumer = new NumberConsumer(channel);

        Thread threadNumberProducer = new Thread(numberProducer);
        Thread threadNumberConsumer = new Thread(numberConsumer);

        threadNumberProducer.start();
        threadNumberConsumer.start();

        threadNumberConsumer.join();
        threadNumberProducer.join();
    }
}
