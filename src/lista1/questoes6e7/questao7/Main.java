package lista1.questoes6e7.questao7;

import lista1.questoes6e7.channel.Channel;

public class Main {

    public static void main(String args[]) throws InterruptedException {
        Channel<String> producerChannel = new Channel<>("producerChannel");
        Channel<String> filteredChannel = new Channel<>("filteredChannel");

        StringProducer stringProducer = new StringProducer(producerChannel);
        StringFilter stringFilter = new StringFilter(producerChannel, filteredChannel);
        ConsumerFilterString consumerFilterString = new ConsumerFilterString(filteredChannel);

        Thread stringProducerThread = new Thread(stringProducer);
        Thread filterStringThread = new Thread(stringFilter);
        Thread consumerFilterStringThread = new Thread(consumerFilterString);

        stringProducerThread.start();
        filterStringThread.start();
        consumerFilterStringThread.start();

        stringProducerThread.join();
        filterStringThread.join();
        consumerFilterStringThread.join();
    }
}