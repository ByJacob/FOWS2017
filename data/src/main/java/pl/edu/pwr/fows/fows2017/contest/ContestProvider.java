package pl.edu.pwr.fows.fows2017.contest;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import pl.edu.pwr.fows.fows2017.base.OkHttpProvider;
import pl.edu.pwr.fows.fows2017.declarationInterface.AuthInterface;
import pl.edu.pwr.fows.fows2017.declarationInterface.DatabaseInterface;
import pl.edu.pwr.fows.fows2017.declarationInterface.SharedPreferencesDataInterface;
import pl.edu.pwr.fows.fows2017.entity.ContestQuestion;
import pl.edu.pwr.fows.fows2017.sharedPreferencesAPI.SharedPreferencesAPIProvider;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 26.11.2017.
 */

public class ContestProvider extends OkHttpProvider{
    private String urlQuestion;
    private String urlVersion;
    private SharedPreferencesDataInterface sharedPreferences;
    private DatabaseInterface databaseInterface;
    private AuthInterface authInterface;
    private String version;

    public ContestProvider(String urlQuestion, String urlVersion,
                           SharedPreferencesDataInterface sharedPreferences,
                           DatabaseInterface databaseInterface, AuthInterface authInterface) {
        this.urlQuestion = urlQuestion;
        this.urlVersion = urlVersion;
        this.sharedPreferences = sharedPreferences;
        this.databaseInterface = databaseInterface;
        this.authInterface = authInterface;
    }

    public List<ContestQuestion> getQuestion() throws IOException {
        Gson gson = new Gson();
        ContestQuestion[] tmp = gson.fromJson(run(urlQuestion), ContestQuestion[].class);
        getVersion();
        return Arrays.asList(tmp);
    }

    private void getVersion() throws IOException {
        this.version = run(urlVersion);
    }

    public boolean sendAnswers(HashMap<String, String> answers) {
        if(authInterface.getUserUid()!=null){
            databaseInterface.sendAnswer(answers, this.version, authInterface.getUserDisplayName()+"-"+authInterface.getUserEmail());
            sharedPreferences.save(SharedPreferencesAPIProvider.TAG_CONTEST, this.version);
            return true;
        }
        return false;
    }
}
