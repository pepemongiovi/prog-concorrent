package lista1.questoes2_3_4.questao4;

import lista1.questoes2_3_4.questao2.HttpRequest;

import java.util.concurrent.*;

/**
 * Call {@code httpRequest} from {@link HttpRequest} until {@link DoneVerifier} finally.
 *
 * @author Thaynan Nunes
 */
public class InfinityHttpRequest {

    private HttpRequest httpRequest;

    public InfinityHttpRequest() {
        httpRequest = new HttpRequest();
    }

    /**
     * Executes he {@link HttpRequest} until {@link DoneVerifier} indicates that to finish.
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void execHttpRequest() throws ExecutionException, InterruptedException {
        DoneVerifier doneVerifier = new DoneVerifier();
        FutureTask<Long> future = new FutureTask<>(doneVerifier);
        ExecutorService executor = Executors.newFixedThreadPool(1);

        executor.execute(future);

        while(!future.isDone()) {
            System.out.println(httpRequest.reliableRequest());
        }
        executor.shutdown();
    }
}