package pl.edu.pwr.fows.fows2017.tools;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
