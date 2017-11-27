package pl.edu.pwr.fows.fows2017.lecture;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.edu.pwr.fows.fows2017.base.OkHttpProvider;
import pl.edu.pwr.fows.fows2017.declarationInterface.SharedPreferencesDataInterface;
import pl.edu.pwr.fows.fows2017.entity.PrelegentsDay;
import pl.edu.pwr.fows.fows2017.sharedPreferencesAPI.SharedPreferencesAPIProvider;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 15.08.2017.
 */

public class LectureProvider extends OkHttpProvider {

    private final List<PrelegentsDay> lectures = new ArrayList<>();
    private final String url;
    private final SharedPreferencesDataInterface gatewaySharedPref;

    public LectureProvider(String url, SharedPreferencesDataInterface gatewaySharedPref) {
        this.url = url;
        this.gatewaySharedPref = gatewaySharedPref;
    }

    public List<PrelegentsDay> getLectures() throws IOException {
        Gson gson = new Gson();
        String response = run(url);
        gatewaySharedPref.save(SharedPreferencesAPIProvider.TAG_LECTURES, response);
        PrelegentsDay[] tmp = gson.fromJson(response, PrelegentsDay[].class);
        lectures.clear();
        lectures.addAll(Arrays.asList(tmp));
        return lectures;
    }
}
