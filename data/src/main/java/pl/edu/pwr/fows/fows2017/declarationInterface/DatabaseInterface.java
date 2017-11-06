package pl.edu.pwr.fows.fows2017.declarationInterface;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 01.11.2017.
 */

public interface DatabaseInterface {
    int sendQuestionnaireAnswers(String uuid, Integer number, String question, String answer);

    int sendUser(String uid, String name, String surname, String email, String university, String company, Boolean isVerify);

    int sendUser(String uid, Boolean isVerify);
}
