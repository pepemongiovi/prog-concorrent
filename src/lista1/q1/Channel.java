package lista1.q1;

public interface Channel {
	public void putMessage(String message);
	public String takeMessage();
}
