package lista1.q2;

public class Main {

    public static void main(String args[]) throws InterruptedException {
        HttpRequest httpRequest = new HttpRequest();

        String response = httpRequest.request("mirror");

        System.out.println(response);
    }
}
