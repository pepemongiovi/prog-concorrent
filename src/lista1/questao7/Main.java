package lista1.questao7;

import lista1.channel.Channel;
import lista1.channel.ChannelImpl;

public class Main {

    public static void main(String args[]) throws InterruptedException {
        Channel<String> producerChannel = new ChannelImpl<>();
        Channel<String> filteredChannel = new ChannelImpl<>();

        StringProducer stringProducer = new StringProducer(producerChannel);
        FilterString filterString = new FilterString(producerChannel, filteredChannel);
        ConsumerFilterString consumerFilterString = new ConsumerFilterString(filteredChannel);

        Thread stringProducerThread = new Thread(stringProducer);
        Thread filterStringThread = new Thread(filterString);
        Thread consumerFilterStringThread = new Thread(consumerFilterString);

        stringProducerThread.start();
        filterStringThread.start();
        consumerFilterStringThread.start();

        stringProducerThread.join();
        filterStringThread.join();
        consumerFilterStringThread.join();
    }
}