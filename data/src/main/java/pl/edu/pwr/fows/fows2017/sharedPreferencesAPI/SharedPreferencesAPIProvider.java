package pl.edu.pwr.fows.fows2017.sharedPreferencesAPI;


import java.util.ArrayList;
import java.util.List;

import pl.edu.pwr.fows.fows2017.entity.FacebookPost;
import pl.edu.pwr.fows.fows2017.parser.JsonParserFacebookPosts;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 08.08.2017.
 */

public class SharedPreferencesAPIProvider {

    public static final String TAG_FACEBOOK_POSTS = "_facebook_posts";

    private SharedPreferencesDataInterface API;

    public SharedPreferencesAPIProvider(SharedPreferencesDataInterface API) {
        this.API = API;
    }

    public void saveFacebookPosts(String string){
        API.save(TAG_FACEBOOK_POSTS, string);
    }

    public List<FacebookPost> getFacebookPosts(){
        String json =  API.get(TAG_FACEBOOK_POSTS, "");
        if (json.length()<1)
            return new ArrayList<>();
        return JsonParserFacebookPosts.parseJson(json);
    }
}
