package lista1.questoes2_3_4.questao2;

import java.util.concurrent.ExecutionException;

/**
 * Class to be test {@link HttpRequest}.
 *
 * @author Thaynan Nunes
 */
public class Main {

    public static void main(String args[]) throws ExecutionException, InterruptedException {

        HttpRequest httpRequest = new HttpRequest();

        String response = httpRequest.reliableRequest();
        
        System.out.println(response);
    }
}