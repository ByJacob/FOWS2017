package pl.edu.pwr.fows.fows2017.questionnaire;

import java.io.IOException;
import java.util.List;

import pl.edu.pwr.fows.fows2017.entity.Question;
import pl.edu.pwr.fows.fows2017.gateway.QuestionGateway;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 22.08.2017.
 */

public class QuestionnaireClient implements QuestionGateway {

    private static final String URL = "http://fows.pwr.edu.pl/sections/android-questions.php?android";
    private QuestionnaireProvider provider;

    public QuestionnaireClient() {
        this.provider = new QuestionnaireProvider(URL);
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
}
