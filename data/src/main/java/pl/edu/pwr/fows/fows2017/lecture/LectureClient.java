package pl.edu.pwr.fows.fows2017.lecture;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import pl.edu.pwr.fows.fows2017.declarationInterface.SharedPreferencesDataInterface;
import pl.edu.pwr.fows.fows2017.entity.Lecture;
import pl.edu.pwr.fows.fows2017.entity.PrelegentsDay;
import pl.edu.pwr.fows.fows2017.gateway.LectureGateway;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 15.08.2017.
 */

public class LectureClient implements LectureGateway {

    private final String URL = "https://fows-2017.firebaseio.com/agenda.json";
    private LectureProvider provider;

    @Inject
    public LectureClient(SharedPreferencesDataInterface gatewaySharedPref) {
        provider = new LectureProvider(URL, gatewaySharedPref);
    }



    @Override
    public List<PrelegentsDay> getPrelegentsDay() throws IOException {
        return provider.getLectures();
    }
}
