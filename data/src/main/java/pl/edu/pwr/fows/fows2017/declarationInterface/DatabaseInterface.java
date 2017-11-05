package pl.edu.pwr.fows.fows2017.declarationInterface;

import java.util.Map;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 01.11.2017.
 */

public interface DatabaseInterface {
    int sendQuestionnaireAnswers(String uuid, Integer number, String question, String answer);
}
