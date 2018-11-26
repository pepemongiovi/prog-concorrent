package lista1.questoes6_7.channel;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Channel to put or take queued messages.
 *
 * @param <T> Type of message to put on queue
 *
 * @author Thaynan Nunes
 */
public class Channel<T> {

    private final String name;
    private final Queue<T> messageQueue;
    private volatile boolean isClosed;

    public Channel(String name) {
        this.name = name;
        messageQueue = new LinkedList<T>();
        isClosed = false;
    }

    /**
     * Returns the isClosed's value.
     *
     * @return {@code true} if is closed, false otherwise
     */
    public boolean isClosed() {
        return isClosed;
    }

    /**
     * Closes the channel if the queue is empty.
     */
    public void closeChannel() {
        synchronized (this.messageQueue) {
            this.isClosed = true;
            // It is necessary, because there may be threads waiting to take messages in
            // the queue, so it wakes them up.
            this.messageQueue.notifyAll();
        }
    }

    /**
     * Puts a new message into queue.
     *
     * @param message Message to be putted
     */
    public void put(T message) {
        if(this.isClosed()) {
            throw new RuntimeException("Channel is closed!");
        }
        synchronized (this.messageQueue) {
            this.messageQueue.add(message);
            this.messageQueue.notifyAll();
            System.out.println("MSG ADD into " + this.name + ": " + message);
        }
    }

    /**
     * Takes the next message on the queue.
     *
     * @return Gotten message
     * @throws InterruptedException
     */
    public T take() throws InterruptedException {
        synchronized (this.messageQueue) {
            while(this.messageQueue.isEmpty() && !this.isClosed()) {
                this.messageQueue.wait();
            }

            T message = this.messageQueue.poll();
            this.messageQueue.notifyAll();
            return message;
        }
    }

    /**
     * Returns is channel's queue is empty.
     *
     * @return {@code true} if queue is empty, false otherwise
     */
    public boolean isEmpty() {
        synchronized (this.messageQueue) {
            return this.messageQueue.isEmpty();
        }
    }
}