package lista1.questoes2_3_4.mirror;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 *  Thread that get response.
 *
 * @author Thaynan Nunes
 */
public class Mirror implements Callable<String> {

    private static final String response = "I'm response!";
    private final String name;

    public Mirror(String name) {
        this.name = name;
    }

    /**
     * Obtains response from {@link Mirror} and calculates a random fibonacci.
     *
     * @return response
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        Random random = new Random();
        Integer n = random.nextInt(20); // If you wants testing Questão 2.
        //Integer n = random.nextInt(100); // If you wants testing Questão 3.
        // System.out.println("Mirror " + name + " calculating fibonacci " + n); // if you wants testing Questões 2 e 3.
        int r = fibonacci(n);
        return "Mirror " + name + " with response: " + response;
    }

    /**
     * Calculates a fibonacci.
     *
     * @param n Fibonacci's index to be calculated
     * @return value of fibonacci's index
     */
    private Integer fibonacci(Integer n) {
        if(n == 0 || n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}