package lista1.questoes2_3_4.questao3;

import lista1.questoes2_3_4.mirror.Mirror;
import lista1.questoes2_3_4.questao3.exception.ThreadsTimeLimitException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Class that request a response to the {@link Mirror}.
 *
 * @author Thaynan Nunes
 */
public class HttpRequest {

    private static final int MIRRORS_NUMBER = 3;
    private static final long TIME_LIMIT = 2;

    public HttpRequest() { }

    /**
     * Request the response to {@link Mirror}.
     *
     * @return response
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws ThreadsTimeLimitException
     */
    public String reliableRequest() throws ExecutionException, InterruptedException, ThreadsTimeLimitException {
        return this.request("mirror");
    }

    /**
     * Request the response to {@link Mirror}, if took more than 2 seconds, so throws
     * {@link ThreadsTimeLimitException}.
     *
     * @param serverName Server's name to be requested
     * @return response
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws ThreadsTimeLimitException
     */
    public String request(String serverName) throws InterruptedException, ExecutionException, ThreadsTimeLimitException {
        ExecutorService threadPool = Executors.newFixedThreadPool(MIRRORS_NUMBER);
        List<Callable<String>> tasks = new ArrayList<>();

        createAndAddMirror(serverName + "1.com", tasks);
        createAndAddMirror(serverName + "2.br", tasks);
        createAndAddMirror(serverName + "3.edu", tasks);

        try {
            String response = threadPool.invokeAny(tasks, TIME_LIMIT, TimeUnit.SECONDS);
            threadPool.shutdownNow();
            return response;
        } catch (TimeoutException e) {
            throw new ThreadsTimeLimitException("The execution took more than two seconds.");
        }
    }

    /**
     * Creates and adds new {@link Mirror} into {@link Callable} task.
     *
     * @param mirrorName {@link Mirror}'s name to be added
     * @param tasks Task where {@link Mirror} to be added
     * @return {@link Mirror} created
     */
    private Mirror createAndAddMirror(String mirrorName, List<Callable<String>> tasks) {
        Mirror mirror = new Mirror(mirrorName);
        tasks.add(mirror);
        return mirror;
    }
}