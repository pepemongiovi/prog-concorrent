package lista1.questoes6e7.questao6;

import lista1.questoes6e7.channel.Channel;

public class Main {

    public static void main(String args[]) throws InterruptedException {
        Channel<Integer> channel = new Channel<>("channel");

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
