package pl.edu.pwr.fows.fows2017.base;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 12.08.2017.
 */

public class OkHttpProvider {
    private final OkHttpClient client;

    protected OkHttpProvider() {
        this.client = new OkHttpClient();
    }

    protected String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
