package pl.edu.pwr.fows.fows2017.declarationInterface;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public interface AuthInterface {
    Boolean addUserAndLogin(String email, String password, String displayName);

    Boolean login(String email, String password);

    Boolean signOut();

    String getToken();

    String getUserUid();

    String getUserEmail();

    String getUserDisplayName();

    Boolean sendEmailVerification();

    Boolean sendPasswordResetEmail(String email);

    Boolean isEmailVerified();

    Boolean updateEmail(String email, String password);

    Boolean updatePassword(String password);

    Boolean updateDisplayName(String displayName);
}
