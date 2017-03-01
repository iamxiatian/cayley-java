package cayley;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Tian Xia
 *         Mar 01, 2017 12:52 PM
 */
class OkHttpKit {

    static String post(String url, String context) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS).writeTimeout(20, TimeUnit.SECONDS).build();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
        RequestBody requestBody = RequestBody.create(mediaType, context);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code: " + response);
            }

            return new String(response.body().bytes(),"utf-8");
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

}
