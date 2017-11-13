package pl.edu.pwr.fows.fows2017.questionnaire;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import pl.edu.pwr.fows.fows2017.base.OkHttpProvider;
import pl.edu.pwr.fows.fows2017.declarationInterface.AuthInterface;
import pl.edu.pwr.fows.fows2017.declarationInterface.DatabaseInterface;
import pl.edu.pwr.fows.fows2017.declarationInterface.SharedPreferencesDataInterface;
import pl.edu.pwr.fows.fows2017.entity.Question;
import pl.edu.pwr.fows.fows2017.entity.Questionnaire;
import pl.edu.pwr.fows.fows2017.sharedPreferencesAPI.SharedPreferencesAPIProvider;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 22.08.2017.
 */

public class QuestionnaireProvider extends OkHttpProvider {

    private final DatabaseInterface databaseInterface;
    private AuthInterface authInterface;
    private String url;
    private List<Question> questions = new ArrayList<>();
    private SharedPreferencesDataInterface sharedPreferences;
    private String version;

    public QuestionnaireProvider(String url, SharedPreferencesDataInterface sharedPreferences,
                                 DatabaseInterface databaseInterface, AuthInterface authInterface) {
        this.url = url;
        this.sharedPreferences = sharedPreferences;
        this.databaseInterface = databaseInterface;
        this.authInterface = authInterface;
    }

    public List<Question> getQuestion() throws IOException {
        if (questions.size() < 1)
            getDate();
        return questions;
    }

    public int sendAnswer(List<Question> questionList) throws IOException {
        boolean isAnonymous = false;
        if (authInterface.getUserUid().length() < 1) {
            isAnonymous = true;
            authInterface.loginAnonymously();
        }
        long timeMillis = System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString();
        JSONArray parent = new JSONArray();
        for (int i = 0; i < questionList.size(); i++) {
            JSONObject child = new JSONObject();
            String questionString = questionList.get(i).getQuestionPL();
            String answer = questionList.get(i).getUserAnswer();
            String[] splitAnswer = answer.split(":");
            String answerString = answer.replace(splitAnswer[0] + ":", "");
            child.put("question", questionString);
            child.put("answer", answerString);
            parent.put(child);
            //databaseInterface.sendQuestionnaireAnswers(uuid, i, questionString, answerString);
        }
        databaseInterface.sendQuestionnaireAnswers(timeMillis + uuid, parent);
        sharedPreferences.save(SharedPreferencesAPIProvider.TAG_QUESTIONNAIRE, version);
        if (isAnonymous)
            authInterface.signOut();
        return 1;
    }

    public String getVersion() throws IOException {
        getDate();
        return version;
    }

    private void getDate() throws IOException {
        Gson gson = new Gson();
        Questionnaire questionnaire = gson.fromJson(run(url), Questionnaire.class);
        version = String.valueOf(questionnaire.getVersion());
        questions.clear();
        questions.addAll(questionnaire.getQuestion());
        for (Question question : questions) {
            if (question.getAnswer() != null) {
                question.setAnswer(question.getAnswer());
            }
            if (question.getAnswerPL() == null)
                question.setAnswerPL(new ArrayList<>());
            if (question.getAnswerEN() == null)
                question.setAnswerEN(new ArrayList<>());
        }
    }
}
