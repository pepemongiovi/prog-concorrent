package lista1.channel;

public interface Channel<T> {

	public void putMessage(T message);

	public T takeMessage();
}