package pl.edu.pwr.fows.fows2017.questionnaire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.edu.pwr.fows.fows2017.base.OkHttpProvider;
import pl.edu.pwr.fows.fows2017.entity.Question;
import pl.edu.pwr.fows.fows2017.parser.JsonParserQuestions;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 22.08.2017.
 */

public class QuestionnaireProvider extends OkHttpProvider{

    private String url;
    private List<Question> questions = new ArrayList<>();

    public QuestionnaireProvider(String url) {
        this.url = url;
    }

    public List<Question> getQuestion() throws IOException {
        if(questions.size()<1)
            getDate();
        return questions;
    }

    private void getDate() throws IOException {
        String response = run(url+ "?android");
        questions.addAll(JsonParserQuestions.parseJson(response));
    }
}
