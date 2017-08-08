package pl.edu.pwr.fows.fows2017.facebookPost;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pl.edu.pwr.fows.fows2017.entity.FacebookPost;
import pl.edu.pwr.fows.fows2017.parser.JsonParserFacebookPosts;
import pl.edu.pwr.fows.fows2017.sharedPreferencesAPI.SharedPreferencesAPIProvider;
import pl.edu.pwr.fows.fows2017.sharedPreferencesAPI.SharedPreferencesDataInterface;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.08.2017.
 */

public class FacebookProvider{

    private final List<FacebookPost> posts = new ArrayList<>();
    private final OkHttpClient client;
    private final String url;
    private final SharedPreferencesDataInterface gatewaySharedPref;

    public FacebookProvider(String url, SharedPreferencesDataInterface gatewaySharedPref) {
        this.client = new OkHttpClient();
        this.url = url;
        this.gatewaySharedPref = gatewaySharedPref;
    }

    public List<FacebookPost> getPosts() throws Exception{
        String response;
        if (posts.size() < 1 ) {
            response = run(url);
            gatewaySharedPref.save(SharedPreferencesAPIProvider.TAG_FACEBOOK_POSTS, response);
            List<FacebookPost> tempPosts = JsonParserFacebookPosts.parseJson(response);
            for(int i=0; i< tempPosts.size(); i++){
                posts.add(tempPosts.get(i));
            }
        }
        return posts;
    }

    private String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
