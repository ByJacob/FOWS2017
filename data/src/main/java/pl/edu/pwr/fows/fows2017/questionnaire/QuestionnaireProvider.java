package pl.edu.pwr.fows.fows2017.questionnaire;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.edu.pwr.fows.fows2017.base.OkHttpProvider;
import pl.edu.pwr.fows.fows2017.declarationInterface.SharedPreferencesDataInterface;
import pl.edu.pwr.fows.fows2017.entity.Question;
import pl.edu.pwr.fows.fows2017.parser.JsonParserQuestions;
import pl.edu.pwr.fows.fows2017.sharedPreferencesAPI.SharedPreferencesAPIProvider;
import sun.rmi.runtime.Log;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 22.08.2017.
 */

public class QuestionnaireProvider extends OkHttpProvider{

    private String url;
    private List<Question> questions = new ArrayList<>();
    private SharedPreferencesDataInterface sharedPreferences;
    private String version;

    public QuestionnaireProvider(String url, SharedPreferencesDataInterface sharedPreferences) {
        this.url = url;
        this.sharedPreferences = sharedPreferences;
    }

    public List<Question> getQuestion() throws IOException {
        if(questions.size()<1)
            getDate();
        return questions;
    }

    public int sendAnswer(List<Question> questionList) throws IOException {
        JSONArray answers = new JSONArray();
        for(int i=0; i<questionList.size(); i++)
        {
            JSONObject object = new JSONObject();
            object.put("question", questionList.get(i).getQuestionPL());
            String answer = questionList.get(i).getUserAnswer();
            String[] splitAnswer = answer.split(":");
            object.put("answer", answer.replace(splitAnswer[0]+":", ""));
            answers.put(object);
        }
        sharedPreferences.save(SharedPreferencesAPIProvider.TAG_QUESTIONNAIRE, version);
        return sendData(url, answers.toString());
    }

    public String getVersion() throws IOException {
        getDate();
        return version;
    }

    private void getDate() throws IOException {
        String response = run(url+ "?android");
        version = JsonParserQuestions.getVersion(response);
        questions.clear();
        questions.addAll(JsonParserQuestions.parseJson(response));
    }
}
