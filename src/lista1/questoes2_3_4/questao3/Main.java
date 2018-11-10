package lista1.questoes2_3_4.questao3;

import lista1.questoes2_3_4.questao3.exception.ThreadsTimeLimitException;

import java.util.concurrent.ExecutionException;

/**
 * Class to be test {@link HttpRequest}.
 *
 * @author Thaynan Nunes
 */
public class Main {

    public static void main(String args[]) throws ExecutionException, InterruptedException, ThreadsTimeLimitException {
        HttpRequest httpRequest = new HttpRequest();

        String response = httpRequest.reliableRequest();

        System.out.println(response);
    }
}