package lista1.questoes6_7.questao6;

import lista1.questoes6_7.channel.Channel;

public class Main {

    public static void main(String args[]) throws InterruptedException {
        Channel<Integer> channel = new Channel<>("channel");

        NumberProducer numberProducer = new NumberProducer(channel);
        NumberConsumer numberConsumer = new NumberConsumer(channel);
        NumberConsumer numberConsumer2 = new NumberConsumer(channel);

        Thread threadNumberProducer = new Thread(numberProducer);
        Thread threadNumberConsumer = new Thread(numberConsumer);
        Thread threadNumberConsumer2 = new Thread(numberConsumer2);

        threadNumberProducer.start();
        threadNumberConsumer.start();
        threadNumberConsumer2.start();

        threadNumberConsumer.join();
        threadNumberConsumer2.join();
        threadNumberProducer.join();
    }
}
