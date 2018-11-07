package lista1.channel;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChannelImpl<T> implements Channel<T> {

	private final int MAX_CAPACITY = Integer.MAX_VALUE;
	private final int CAPACITY;
	
	private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    
    private final Queue<T> messageQueue;

	public ChannelImpl() {
		this.CAPACITY = MAX_CAPACITY;
		messageQueue = new LinkedList<>();
	}

	public ChannelImpl(int capacity) {
		this.CAPACITY = capacity;
		messageQueue = new LinkedList<>();
	}

	@Override
	public void putMessage(T message) {
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

	@Override
	public T takeMessage() {
		lock.lock();

    	while (this.messageQueue.isEmpty()) {
            try {
                notEmpty.await();
            } catch (InterruptedException e) { }
        }

    	T message = this.messageQueue.poll();

        this.notFull.signalAll();
    	lock.unlock();

    	return message;
	}
}