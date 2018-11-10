package lista1.questoes2_3_4.questao3.exception;

/**
 * Exception to represent time limit of Threads running.
 *
 * @author Thaynan Nunes
 */
public class ThreadsTimeLimitException extends Exception {

    public ThreadsTimeLimitException(String message) {
        super(message);
    }
}