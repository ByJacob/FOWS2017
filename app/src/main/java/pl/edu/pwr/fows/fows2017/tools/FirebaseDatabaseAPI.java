package pl.edu.pwr.fows.fows2017.tools;

import com.google.firebase.database.FirebaseDatabase;

import pl.edu.pwr.fows.fows2017.declarationInterface.FirebaseDatabaseInterface;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 01.11.2017.
 */

public class FirebaseDatabaseAPI implements FirebaseDatabaseInterface {
    private FirebaseDatabase database;

    public FirebaseDatabaseAPI() {
        this.database = FirebaseDatabase.getInstance().getReference().getDatabase();
    }
}
