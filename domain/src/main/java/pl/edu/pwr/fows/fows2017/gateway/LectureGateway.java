package pl.edu.pwr.fows.fows2017.gateway;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import pl.edu.pwr.fows.fows2017.entity.Lecture;
import pl.edu.pwr.fows.fows2017.entity.PrelegentsDay;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 15.08.2017.
 */

public interface LectureGateway {

    List<PrelegentsDay> getPrelegentsDay() throws IOException;

}
