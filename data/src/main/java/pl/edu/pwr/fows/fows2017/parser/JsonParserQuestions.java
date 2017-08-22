package pl.edu.pwr.fows.fows2017.parser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pwr.fows.fows2017.entity.Question;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 22.08.2017.
 */

public class JsonParserQuestions {

    public static List<Question> parseJson(String json){
        List<Question> response = new ArrayList<>();
        JSONArray array = new JSONArray(json);
        for(int i=0; i<array.length(); i++){
            JSONObject question = array.getJSONObject(i);
            Question.TYPE type = Question.getType(question.getString("type"));
            String questionPL = question.getString("questionPL");
            String questionEN = question.getString("questionEN");
            List<String> answerPL = new ArrayList<>();
            List<String> answerEN = new ArrayList<>();
            if(question.has("answer")){
                JSONArray answerArray = question.getJSONArray("answer");
                for(int j=0; j<answerArray.length(); j++){
                    answerPL.add(answerArray.getString(j));
                    answerEN.add(answerArray.getString(j));
                }
            } else if (question.has("answerPL") && question.has("answerEN")){
                JSONArray answerPLArray = question.getJSONArray("answerPL");
                JSONArray answerENArray = question.getJSONArray("answerEN");
                for(int j=0; j<answerPLArray.length() && j<answerENArray.length(); j++){
                    answerPL.add(answerPLArray.getString(j));
                    answerEN.add(answerENArray.getString(j));
                }
            }
            response.add(Question.Builder.aQuestion().withType(type).withAnswersEN(answerEN)
                    .withAnswersPL(answerPL).withQuestionEN(questionEN).withQuestionPL(questionPL)
                    .build());
        }
        return response;
    }
}
