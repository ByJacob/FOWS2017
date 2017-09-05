package pl.edu.pwr.fows.fows2017.firebaseToken;

import java.io.IOException;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.gateway.FirebaseTokenGateway;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.08.2017.
 */

public class FirebaseTokenClient implements FirebaseTokenGateway {

    public static String URL = "http://fows.pwr.edu.pl/sections/android-saveTokens.php";

    private FirebaseTokenProvider provider;

    @Inject
    public FirebaseTokenClient() {
        provider = new FirebaseTokenProvider(URL);
    }

    @Override
    public Boolean sendToken(String token, String language) throws IOException {
        return provider.sendToken(token, language);
    }
}
