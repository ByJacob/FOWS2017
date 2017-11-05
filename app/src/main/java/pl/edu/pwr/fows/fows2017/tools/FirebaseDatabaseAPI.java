package pl.edu.pwr.fows.fows2017.tools;

import com.google.firebase.database.FirebaseDatabase;

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
    public int sendQuestionnaireAnswers(String uuid, Integer numer, String question, String answer) {
        database.getReference("questionnaireAnswers").child(uuid).child(String.valueOf(numer)).child("question").setValue(question);
        database.getReference("questionnaireAnswers").child(uuid).child(String.valueOf(numer)).child("answer").setValue(answer);
        return 1;
    }
}
