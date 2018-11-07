package lista1.questoes6e7.channel;

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

    public boolean isClosed() {
        return isClosed;
    }

    public boolean isOpen() {
        return !this.isClosed() || !this.isEmpty();
    }

    /**
     * Closes the channel.
     */
    public void closeChannel() {
        this.isClosed = true;
        synchronized (this.messageQueue) {
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
            return message;
        }
    }

    /**
     * Returns {@code true} if the messageQueue is empty, otherwise
     * returns {@code false}.
     *
     * @return {@code true} if the messageQueue is empty, otherwise
     *          returns {@code false}.
     */
    public boolean isEmpty() {
        return this.messageQueue.isEmpty();
    }
}