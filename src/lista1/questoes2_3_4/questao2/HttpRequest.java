package lista1.questoes2_3_4.questao2;

import lista1.questoes2_3_4.mirror.Mirror;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;

/**
 * Class that request a response to the {@link Mirror}.
 *
 * @author Thaynan Nunes
 */
public class HttpRequest {

    private static final int MIRRORS_NUMBER = 3;

    public HttpRequest() { }

    /**
     * Request the response to {@link Mirror}.
     *
     * @return response
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public String reliableRequest() throws ExecutionException, InterruptedException {
        return this.request("mirror");
    }

    /**
     * Request the response to {@link Mirror}.
     *
     * @param serverName Server's name to be requested
     * @return response
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public String request(String serverName) throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newFixedThreadPool(MIRRORS_NUMBER);
        List<Callable<String>> tasks = new ArrayList<>();

        createAndAddMirror(serverName + "1.com", tasks);
        createAndAddMirror(serverName + "2.br", tasks);
        createAndAddMirror(serverName + "3.edu", tasks);

        String response = threadPool.invokeAny(tasks);

        threadPool.shutdownNow();

        return response;
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