package cayley;

import okhttp3.*;
import robot.http.impl.OkHttpImpl;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by kai on 17/2/18.
 */
public class OkhttpKit {

    private static SSLSocketFactory getCertificates(InputStream inputStream) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);

            int index = 0;
            String certificateAlias = Integer.toString(index++);
            keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(inputStream));

            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param inputStream https证书地址
     * @return
     */
    public static OkHttpClient getClient(InputStream inputStream){
        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(getCertificates(inputStream))
                .build();
        return client;
    }

    public static OkHttpClient getClient(){
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS).writeTimeout(20, TimeUnit.SECONDS).build();
        return client;
    }

    public static FetchResult get(String url,InputStream inputStream) {
        FetchResult result = new FetchResult();
        //创建请求
        Request request = new Request.Builder().url(url).build();
        //接收响应
        try {
            Response response = getClient(inputStream).newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code: " + response);
            }
            // 设置返回的头部信息
            Map<String, String> headerMap = new HashMap<String, String>();
            Headers headers = response.headers();
            for (int i = 0; i < headers.size(); i++) {
                headerMap.put(headers.name(i), headers.value(i));
            }
            result.setHeaders(headerMap);
            result.setUrl(url);
            result.setHttpCode(response.code());
            result.setEncoding(OkHttpImpl.getContentType(response.header("Content-Type"), "utf-8"));
            result.setContent(response.body().bytes());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FetchResult post(String url, String context)  {
        FetchResult result = new FetchResult();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, context);
        Request request = new Request.Builder().url(url).post(body).build();
        try {
            Response response = getClient().newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code: " + response);
            }
            // 设置返回的头部信息
            Map<String, String> headerMap = new HashMap<>();
            Headers headers = response.headers();
            for (int i = 0; i < headers.size(); i++) {
                headerMap.put(headers.name(i), headers.value(i));
            }
            result.setHeaders(headerMap);
            result.setUrl(url);
            result.setHttpCode(response.code());
            result.setEncoding(OkHttpImpl.getContentType(response.header("Content-Type"), "utf-8"));
            result.setContent(response.body().bytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
