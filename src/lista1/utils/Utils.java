package lista1.utils;

public class Utils {

    public static boolean isEven(Integer number) {
        return number % 2 == 0;
    }

    public static void sleep(Integer milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}