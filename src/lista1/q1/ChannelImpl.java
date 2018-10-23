package lista1.q1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChannelImpl implements Channel{
	
	private final int CAPACITY;
	
	private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    
    private Queue<String> msgs = new LinkedList<String>();
	
	public ChannelImpl(int capacity) {
		this.CAPACITY = capacity;
	}

	@Override
	public void putMessage(String message) {
	    lock.lock();
	    
    	while (this.msgs.size() == this.CAPACITY) {
            try {
                notFull.await();
            } catch (InterruptedException e) { }
        }
    	
        this.msgs.add(message);
        System.out.println("MSG SENT: " + message);
        
        this.notEmpty.signalAll();
        lock.unlock();
	}

	@Override
	public String takeMessage() {
		lock.lock();
		
    	while (this.msgs.isEmpty()) {
            try {
                notEmpty.await();
            } catch (InterruptedException e) { }
        }

    	String message = this.msgs.poll();
        System.out.println("MSG READ: " + message);
        
        this.notFull.signalAll();
    	lock.unlock();
    	
    	return message;
	    
	}
}
