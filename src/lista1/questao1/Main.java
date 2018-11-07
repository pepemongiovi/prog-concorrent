package lista1.questao1;

public class Main {
	
	public static void main(String args[]) throws InterruptedException {
        
		Channel channel = new ChannelImpl(5);
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