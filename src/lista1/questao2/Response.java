package lista1.questao2;

/**
 * Class tha store the response value.
 *
 * @author Thaynan Nunes
 */
public class Response {

    private volatile String response;

    public Response() { }

    public Response(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
