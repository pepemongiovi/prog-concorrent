package lista1.questao1;

public interface Channel {

	void putMessage(String message);

	String takeMessage();
}