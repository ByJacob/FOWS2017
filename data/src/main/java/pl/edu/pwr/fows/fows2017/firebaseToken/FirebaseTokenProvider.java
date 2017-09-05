package pl.edu.pwr.fows.fows2017.firebaseToken;

import java.io.IOException;

import pl.edu.pwr.fows.fows2017.base.OkHttpProvider;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.08.2017.
 */

public class FirebaseTokenProvider extends OkHttpProvider{
    private String url;

    public FirebaseTokenProvider(String url) {
        this.url = url;
    }

    public Boolean sendToken(String token, String language) throws IOException {
        String response = run(url + "?token=" + token + "&lang=" + language);
        if(response.contains("ADD"))
            return true;
        else
            return false;
    }
}
