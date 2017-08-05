package pl.edu.pwr.fows.fows2017.facebookPost;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import pl.edu.pwr.fows.fows2017.entity.FacebookPost;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.08.2017.
 */

public class FacebookProvider {

    private final List<FacebookPost> posts = new ArrayList<>();

    public FacebookProvider() {
        String jsonString = "";
        try (BufferedReader br = new BufferedReader(new FileReader("templatePosts.json"))) {
            jsonString = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObj = new JSONObject(jsonString);

        JSONArray data = jsonObj.getJSONArray("data");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
        for(int i=0; i<data.length(); i++){
            JSONObject post = data.getJSONObject(i);
            String full_picture = post.getString("full_picture");
            String message = post.getString("message");
            String picture = post.getString("picture");
            String link = post.getString("link");
            String story = post.getString("story");
            String id = post.getString("id");
            Date created_time = null;
            try {
                created_time = df.parse(post.getString("created_time"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            posts.add(FacebookPost.Builder.aFacebookPost().withFullPicture(full_picture)
                    .withMessage(message).withPicture(picture).withLink(link).withStory(story)
                    .withId(id).withCreatedTime(created_time).build());

        }
    }

    public List<FacebookPost> getPosts() {
        return posts;
    }
}
