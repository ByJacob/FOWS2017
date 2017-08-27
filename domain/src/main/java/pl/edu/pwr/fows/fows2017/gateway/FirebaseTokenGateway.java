package pl.edu.pwr.fows.fows2017.gateway;

import java.io.IOException;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.08.2017.
 */

public interface FirebaseTokenGateway {
    Boolean sendToken(String token, String language) throws IOException;
}
