package pl.edu.pwr.fows.fows2017.tools;

import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

import pl.edu.pwr.fows.fows2017.declarationInterface.DatabaseInterface;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 01.11.2017.
 */

public class FirebaseDatabaseAPI implements DatabaseInterface {
    private FirebaseDatabase database;

    public FirebaseDatabaseAPI() {
        this.database = FirebaseDatabase.getInstance().getReference().getDatabase();
    }

    @Override
    public int sendQuestionnaireAnswers(String uuid, JSONArray json) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap<>();
        for (int i = 0; i < json.length(); i++) {
            try {
                HashMap<String, String> hashChild = new HashMap<>();
                JSONObject child = json.getJSONObject(i);
                Iterator<String> keys = child.keys();
                while (keys.hasNext()){
                    String key = keys.next();
                    hashChild.put(key, child.getString(key));
                }
                hashMap.put(String.valueOf(i), hashChild);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        database.getReference("questionnaireAnswers").child(uuid).setValue(hashMap);
        return 1;
    }

    @Override
    public int sendUser(String uid, String name, String surname, String university, String company, Boolean isVerify) {
        database.getReference("users").child(uid).child("name").setValue(name);
        database.getReference("users").child(uid).child("surname").setValue(surname);
        if (university.length() > 1)
            database.getReference("users").child(uid).child("university").setValue(university);
        if (company.length() > 1)
            database.getReference("users").child(uid).child("company").setValue(company);
        database.getReference("users").child(uid).child("verify").setValue(isVerify);
        return 1;
    }

    @Override
    public int updateUserEmail(String uid, String email) {
        database.getReference("users").child(uid).child("email").setValue(email);
        return 1;
    }

    @Override
    public int updateUserVerify(String uid, Boolean isVerify) {
        database.getReference("users").child(uid).child("verify").setValue(isVerify);
        return 1;
    }
}
