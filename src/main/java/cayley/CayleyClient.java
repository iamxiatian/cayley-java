package cayley;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.*;

/**
 * Main class for Cayley Client
 *
 */
public class CayleyClient {

    private String gremlinUrl = null;

    private String url = "";

    private String version = "v1";

    public CayleyClient() {
        this("http://127.0.0.1:64210", "v1");
    }

    public CayleyClient(String url, String version){
        this.url = url;
        this.version = version;
        this.gremlinUrl =  url + "/api/" + version + "/query/gremlin";
    }

    public String send(GremlinQuery query) throws IOException {
        return send(query.toString());
    }

    public String send(String context) throws IOException {
        return OkHttpKit.post(gremlinUrl, context);
    }

    public static void main(String[] args) throws IOException {
        CayleyClient client = new CayleyClient();
        System.out.println(client.send("g.V('<alice>','<charlie>').Out('<follows>').All()"));
        System.out.println("-----------------");
        GremlinQuery g = new GraphObject().V(new String[]{"<alice>","<charlie>"});
        g.Out("<follows>").All();
        System.out.println(client.send(g));
    }
}
