package pl.edu.pwr.fows.fows2017.sharedPreferencesAPI;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.edu.pwr.fows.fows2017.entity.FacebookPost;
import pl.edu.pwr.fows.fows2017.entity.Lecture;
import pl.edu.pwr.fows.fows2017.entity.Prelegents;
import pl.edu.pwr.fows.fows2017.entity.PrelegentsDay;
import pl.edu.pwr.fows.fows2017.entity.Sponsor;
import pl.edu.pwr.fows.fows2017.declarationInterface.SharedPreferencesDataInterface;
import pl.edu.pwr.fows.fows2017.parser.JsonParserFacebookPosts;
import pl.edu.pwr.fows.fows2017.parser.JsonParserLecture;
import pl.edu.pwr.fows.fows2017.parser.JsonParserSponsor;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 08.08.2017.
 */

public class SharedPreferencesAPIProvider {

    public static final String TAG_FACEBOOK_POSTS = "_facebook_posts";
    public static final String TAG_LECTURES = "_lectures";
    public static final String TAG_QUESTIONNAIRE = "_questionnaire";
    public static final String TAG_USER = "_user";
    public static final String TAG_PASSWORD = "_password";

    private final SharedPreferencesDataInterface API;

    public SharedPreferencesAPIProvider(SharedPreferencesDataInterface API) {
        this.API = API;
    }

    public List<FacebookPost> getFacebookPosts(){
        String json =  API.get(TAG_FACEBOOK_POSTS, "");
        if (json.length()<1)
            return new ArrayList<>();
        return JsonParserFacebookPosts.parseJson(json);
    }

    public List<PrelegentsDay> getLectures() {
        String json = API.get(TAG_LECTURES, "");
        if (json.length()<1)
            return new ArrayList<>();
        Gson gson = new Gson();
        PrelegentsDay[] tmp = gson.fromJson(json, PrelegentsDay[].class);
        return Arrays.asList(tmp);
    }

    public String getQuestionnaireVersion(){
        return API.get(TAG_QUESTIONNAIRE, "");
    }
}
