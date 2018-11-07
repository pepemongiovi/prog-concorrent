package lista1.questao2;

public class Main {

    public static void main(String args[]) throws InterruptedException {
        HttpRequest httpRequest = new HttpRequest();

        String response = httpRequest.request("mirror");

        System.out.println(response);
    }
}
