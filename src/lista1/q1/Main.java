package lista1.q1;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	public static void main(String args[]) throws InterruptedException {
        
		ChannelImpl channel = new ChannelImpl(5);
		Producer producer = new Producer(channel);
		Consumer consumer = new Consumer(channel);
		
        Thread producerThread = new Thread(producer ,"producer-thread");
        Thread consumerThread = new Thread(consumer ,"consumer-thread");
        
        producerThread.start();
        consumerThread.start();
        
        producerThread.join();
        consumerThread.join();
    }
}

