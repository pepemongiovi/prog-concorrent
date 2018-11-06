package lista1.q2;

/**
 * Class that request a response to the {@link Mirror}.
 *
 * @author Thaynan Nunes
 */
public class HttpRequest {

    public HttpRequest() { }

    /**
     * Request the response to {@link Mirror}.
     *
     * @param serverName Server's name to be requested
     * @return response
     * @throws InterruptedException at case the Mirror's threads to be interrupted
     */
    public String request(String serverName) throws InterruptedException {
        return this.reliableRequest();
    }

    /**
     * Private method that instanciate three {@link Mirror} and get the response.
     *
     * @return response
     * @throws InterruptedException at case the Mirror's threads to be interrupted
     */
    private String reliableRequest() throws InterruptedException {
        Response response = new Response();
        Thread threadMirrorConsumer = createAndStartMirrorConsumer(response);
        this.createAndStartMirror(response, "mirror1.com");
        this.createAndStartMirror(response, "mirror2.br");
        this.createAndStartMirror(response, "mirror3.edu");

        threadMirrorConsumer.join();

        return response.getResponse();
    }

    /**
     * Create and start a {@link MirrorConsumer} that wait response from {@link Mirror}.
     *
     * @param response {@link Response} to be obtained
     * @return {@link MirrorConsumer}'s Thread
     */
    private Thread createAndStartMirrorConsumer(Response response) {
        MirrorConsumer mirrorConsumer = new MirrorConsumer(response);
        Thread thread = new Thread(mirrorConsumer);
        thread.start();
        return thread;
    }

    /**
     * Create and start {@link Mirror} to get response.
     *
     * @param response Object that contains string response
     */
    private Thread createAndStartMirror(Response response, String serverName) {
        Mirror mirror = new Mirror(response, serverName);
        Thread thread = new Thread(mirror);
        thread.start();
        return thread;
    }
}