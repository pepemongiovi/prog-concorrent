package lista1.q2;

/**
 * Class that wait for {@link Response} from a {@link Mirror}.
 *
 * @author Thaynan Nunes
 */
public class MirrorConsumer implements Runnable {

    private final Response response;

    public MirrorConsumer(Response response) {
        this.response = response;
    }

    @Override
    public void run() {
        synchronized (this.response) {
            while (this.response.getResponse() == null) {
                try {
                    this.response.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Obtained Response!");
        }
    }
}