package pl.edu.pwr.fows.fows2017.lecture;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import pl.edu.pwr.fows.fows2017.base.OkHttpProvider;
import pl.edu.pwr.fows.fows2017.declarationInterface.SharedPreferencesDataInterface;
import pl.edu.pwr.fows.fows2017.entity.Lecture;
import pl.edu.pwr.fows.fows2017.parser.JsonParserLecture;
import pl.edu.pwr.fows.fows2017.sharedPreferencesAPI.SharedPreferencesAPIProvider;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 15.08.2017.
 */

public class LectureProvider extends OkHttpProvider {

    private final List<Lecture> lectures = new ArrayList<>();
    private final String url;
    private final SharedPreferencesDataInterface gatewaySharedPref;

    public LectureProvider(String url, SharedPreferencesDataInterface gatewaySharedPref) {
        this.url = url;
        this.gatewaySharedPref = gatewaySharedPref;
    }

    public List<Lecture> getLectures() throws IOException {
        String response;
        if (lectures.size() < 1) {
            response = run(url);
            gatewaySharedPref.save(SharedPreferencesAPIProvider.TAG_LECTURES, response);
            List<Lecture> tmpLectures = JsonParserLecture.parseJson(response);
            for(int i=0; i<tmpLectures.size(); i++)
                lectures.add(tmpLectures.get(i));
        }
        return lectures;
    }
}
