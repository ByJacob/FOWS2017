package pl.edu.pwr.fows.fows2017.parser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pl.edu.pwr.fows.fows2017.entity.Lecture;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 15.08.2017.
 */

public class JsonParserLecture {

    public static List<Lecture> parseJson(String json) {
        List<Lecture> response = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(json);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy' 'HH:mm", Locale.US);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject day = jsonArray.getJSONObject(i);
            String date = day.getString("date");
            JSONArray prelegents = day.getJSONArray("prelegents");
            for(int j=0; j<prelegents.length(); j++){
                JSONObject lecture = prelegents.getJSONObject(j);
                String startTime = date + " " + lecture.getString("startTime");
                String endTime = date + " " + lecture.getString("endTime");
                String descriptionPL = lecture.getString("descriptionPL");
                String descriptionEN = lecture.getString("descriptionEN");
                String logo = lecture.getString("logo");
                String prelegentName = lecture.getString("prelegent");
                String prelegentPicture = lecture.getString("prelegentPicture");
                String prelegentPictureSmall = lecture.getString("prelegentPictureSmall");
                String company = lecture.getString("company");
                try {
                    response.add(Lecture.Builder.aLecture().withStartTime(df.parse(startTime))
                            .withEndTime(df.parse(endTime)).withDescriptionPL(descriptionPL)
                            .withDescriptionEN(descriptionEN).withLogoURL(logo)
                            .withSpeakerPicture(prelegentPicture).withCompany(company)
                            .withSpeakerPictureSmall(prelegentPictureSmall)
                            .withSpeaker(prelegentName).build());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }
}
