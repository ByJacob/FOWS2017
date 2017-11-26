package pl.edu.pwr.fows.fows2017.parser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import pl.edu.pwr.fows.fows2017.entity.FacebookPost;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 08.08.2017.
 */

public class JsonParserFacebookPosts {

    public static List<FacebookPost> parseJson(String jsonString) {
        List<FacebookPost> posts = new ArrayList<>();
        JSONObject jsonObj = new JSONObject(jsonString);

        JSONArray data = jsonObj.getJSONArray("data");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ssZ", Locale.US);
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        for (int i = 0; i < data.length(); i++) {
            JSONObject post = data.getJSONObject(i);
            if(!post.has("full_picture"))
                continue;
            String full_picture = post.getString("full_picture");
            String message = post.getString("message");
            String picture = post.getString("picture");
            String link = post.getString("link");
            String story;
            if (post.has("story"))
                story = post.getString("story");
            else
                story = "";
            String created_time_tmp = post.getJSONObject("created_time").getString("date").split("\\.")[0];
            String timezone_time_tmp = post.getJSONObject("created_time").getString("timezone").replace(":", "");
            String id = post.getString("id").split("_")[1];
            Date created_time = null;
            try {
                created_time = df.parse(created_time_tmp + timezone_time_tmp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            posts.add(FacebookPost.Builder.aFacebookPost().withFullPicture(full_picture)
                    .withMessage(message).withPicture(picture).withLink(link).withStory(story)
                    .withId(id).withCreatedTime(created_time).build());

        }
        return posts;
    }
}
