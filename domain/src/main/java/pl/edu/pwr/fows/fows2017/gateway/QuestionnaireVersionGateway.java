package pl.edu.pwr.fows.fows2017.gateway;

import java.io.IOException;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 26.08.2017.
 */

public interface QuestionnaireVersionGateway {
    String getVersion() throws IOException;
}
