package pl.edu.pwr.fows.fows2017.sharedPreferencesAPI;

import java.util.List;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.declarationInterface.SharedPreferencesDataInterface;
import pl.edu.pwr.fows.fows2017.entity.FacebookPost;
import pl.edu.pwr.fows.fows2017.entity.Lecture;
import pl.edu.pwr.fows.fows2017.entity.PrelegentsDay;
import pl.edu.pwr.fows.fows2017.gateway.FacebookPostGateway;
import pl.edu.pwr.fows.fows2017.gateway.LectureGateway;
import pl.edu.pwr.fows.fows2017.gateway.QuestionnaireVersionGateway;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 08.08.2017.
 */

public class SharedPreferencesAPIClient implements FacebookPostGateway, LectureGateway, QuestionnaireVersionGateway {

    private final SharedPreferencesAPIProvider provider;

    @Inject
    public SharedPreferencesAPIClient(SharedPreferencesDataInterface API) {
        provider = new SharedPreferencesAPIProvider(API);
    }

    @Override
    public List<FacebookPost> getPosts() {
        return provider.getFacebookPosts();
    }


    @Override
    public List<PrelegentsDay> getPrelegentsDay() {
        return provider.getLectures();
    }

    @Override
    public String getVersion() {
        return provider.getQuestionnaireVersion();
    }
}
