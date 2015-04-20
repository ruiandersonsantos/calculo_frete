package conexao;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class WebClient {

    private final String url;
    private DefaultHttpClient httpClient;
    private HttpGet httpGet;
    private HttpResponse response;

    public WebClient(String url){

        this.url = url;
    }
    // metodo que faz a comunicação webservice --
    public String obterfrete(){
        try {
            httpClient = new DefaultHttpClient();
            httpGet = new HttpGet(url);
            response = httpClient.execute(httpGet);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
