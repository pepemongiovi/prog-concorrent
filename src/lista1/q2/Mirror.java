package lista1.q2;

/**
 *  Thread that get response and store into {@link Response}.
 *
 * @author Thaynan Nunes
 */
public class Mirror implements Runnable {

    private static final String RESPONSE = "I'm the response!!!";

    private final Response response;
    private final String name;

    public Mirror(Response response, String name) {
        this.response = response;
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (this.response) {
            if(this.response.getResponse() == null) {
                this.response.setResponse(RESPONSE);
                this.response.notifyAll();
            }
        }
    }
}