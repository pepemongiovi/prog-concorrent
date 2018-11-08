package lista1.questoes6e7.questao7;

import lista1.questoes6e7.channel.Channel;

public class Main {

    public static void main(String args[]) throws InterruptedException {
        Channel<String> producerChannel = new Channel<>("producerChannel");
        Channel<String> filteredChannel = new Channel<>("filteredChannel");

        StringProducer stringProducer = new StringProducer(producerChannel);
        StringFilter stringFilter = new StringFilter(producerChannel, filteredChannel);
        ConsumerFilterString consumerFilterString = new ConsumerFilterString(filteredChannel);
        ConsumerFilterString consumerFilterString2 = new ConsumerFilterString(filteredChannel);
        ConsumerFilterString consumerFilterString3 = new ConsumerFilterString(filteredChannel);

        Thread stringProducerThread = new Thread(stringProducer);
        Thread filterStringThread = new Thread(stringFilter);
        Thread consumerFilterStringThread = new Thread(consumerFilterString);
        Thread consumerFilterStringThread2 = new Thread(consumerFilterString2);
        Thread consumerFilterStringThread3 = new Thread(consumerFilterString3);

        stringProducerThread.start();
        filterStringThread.start();
        consumerFilterStringThread.start();
        consumerFilterStringThread2.start();
        consumerFilterStringThread3.start();

        stringProducerThread.join();
        filterStringThread.join();
        consumerFilterStringThread.join();
        consumerFilterStringThread2.join();
        consumerFilterStringThread3.join();
    }
}