package pl.edu.pwr.fows.fows2017.declarationInterface;

import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.HashMap;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 01.11.2017.
 */

public interface DatabaseInterface {
    int sendQuestionnaireAnswers(String uuid, JSONArray json);

    int sendUser(String uid, String name, String surname, String university, String company, Boolean isVerify);

    int updateUserEmail(String uid, String email);

    int updateUserVerify(String uid, Boolean isVerify);

    void sendAnswer(HashMap<String,String> answers, String nameContest, String name);
}
