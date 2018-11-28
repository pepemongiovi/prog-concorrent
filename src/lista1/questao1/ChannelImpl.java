package lista1.questao1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChannelImpl implements Channel {

	private final int CAPACITY;
	
	private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    
    private final Queue<String> messageQueue;

	public ChannelImpl(int capacity) {
		this.CAPACITY = capacity;
		messageQueue = new LinkedList<>();
	}
	
    /**
     * Inserts message in queue
     *
     * @param message Message to be inserted
     */
	@Override
	public void putMessage(String message) {
	    lock.lock();

    	while (this.messageQueue.size() == this.CAPACITY) {
            try {
                notFull.await();
            } catch (InterruptedException e) { }
        }

        this.messageQueue.add(message);
        System.out.println("MSG ADD: " + message);

        this.notEmpty.signalAll();
        lock.unlock();
	}
	
    /**
     * Gets and removes the value on top of the queue
     *
     * @return message that was removed from queue
     */
	@Override
	public String takeMessage() {
		lock.lock();

    	while (this.messageQueue.isEmpty()) {
            try {
                notEmpty.await();
            } catch (InterruptedException e) { }
        }

    	String message = this.messageQueue.poll();

        this.notFull.signalAll();
    	lock.unlock();

    	return message;
	}
}