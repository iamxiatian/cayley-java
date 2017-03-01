package cayley;

import java.io.UnsupportedEncodingException;

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

    public String send(Object context){
        String queryUrl = url + "/api/" + version + "/query/gremlin";
        FetchResult fetchResult = null;
        if(context instanceof GremlinQuery){
            GremlinQuery query = (GremlinQuery)context;
            fetchResult = OkhttpKit.post(queryUrl,query.toString());
        }else if(context instanceof String){
            fetchResult = OkhttpKit.post(queryUrl,(String)context);
        }else{
            return "";
        }
        try {
            String result = new String(fetchResult.getContent(),fetchResult.getEncoding());
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        CayleyClient client = new CayleyClient();
        System.out.println(client.send("g.V('<alice>','<charlie>').Out('<follows>').All()"));
        System.out.println("-----------------");
        GremlinQuery g = new GraphObject().V(new String[]{"<alice>","<charlie>"});
        g.Out("<follows>").All();
        System.out.println(client.send(g));
    }
}
