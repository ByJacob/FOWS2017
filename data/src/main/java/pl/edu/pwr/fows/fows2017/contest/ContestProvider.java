package pl.edu.pwr.fows.fows2017.contest;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import pl.edu.pwr.fows.fows2017.base.OkHttpProvider;
import pl.edu.pwr.fows.fows2017.entity.ContestQuestion;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 26.11.2017.
 */

public class ContestProvider extends OkHttpProvider{
    private String urlQuestion;
    private String urlVersion;
    private String version;

    public ContestProvider(String urlQuestion, String urlVersion) {
        this.urlQuestion = urlQuestion;
        this.urlVersion = urlVersion;
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
}
