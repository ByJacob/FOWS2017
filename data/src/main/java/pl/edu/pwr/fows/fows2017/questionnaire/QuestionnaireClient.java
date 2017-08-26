package pl.edu.pwr.fows.fows2017.questionnaire;

import java.io.IOException;
import java.util.List;

import pl.edu.pwr.fows.fows2017.declarationInterface.SharedPreferencesDataInterface;
import pl.edu.pwr.fows.fows2017.entity.Question;
import pl.edu.pwr.fows.fows2017.gateway.QuestionGateway;
import pl.edu.pwr.fows.fows2017.gateway.QuestionnaireVersionGateway;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 22.08.2017.
 */

public class QuestionnaireClient implements QuestionGateway, QuestionnaireVersionGateway {

    private static final String URL = "http://fows.pwr.edu.pl/sections/android-questions.php";
    //private static final String URL = "http://192.168.0.28/fows2017/sections/android-questions.php";
    private QuestionnaireProvider provider;

    public QuestionnaireClient(SharedPreferencesDataInterface sharedPreferences) {
        this.provider = new QuestionnaireProvider(URL, sharedPreferences);
    }

    @Override
    public List<Question> getQuestionnaire() {
        try {
            return provider.getQuestion();
        } catch (IOException e) {
            e.printStackTrace();
            return null; //TODO change
        }
    }

    @Override
    public int sendQuestionnaire(List<Question> questionList) throws IOException {
        return provider.sendAnswer(questionList);
    }

    @Override
    public String getVersion() throws IOException {
        return provider.getVersion();
    }
}
