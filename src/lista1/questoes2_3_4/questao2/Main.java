package lista1.questoes2_3_4.questao2;

import java.util.concurrent.ExecutionException;

/**
 * Class to be test {@link HttpRequest}.
 *
 * @author Thaynan Nunes
 */
public class Main {

    public static void main(String args[]) throws ExecutionException, InterruptedException {
    	long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        HttpRequest httpRequest = new HttpRequest();

        String response = httpRequest.reliableRequest();

        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        
        long actualMemUsed=afterUsedMem-beforeUsedMem;
        
        System.out.println(response);

        System.out.println("\nMemory used: " + actualMemUsed);
    }
}