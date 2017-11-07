package pl.edu.pwr.fows.fows2017.gateway;

import pl.edu.pwr.fows.fows2017.entity.User;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public interface UserGateway {
    Boolean addUserAndLogin(String email, String password, String name, String surname, String university, String company);
    Boolean login();
    Boolean login(String email, String password);
    Boolean signOut();
    User getUser();
    Boolean sendEmailVerification();
    Boolean updateUser(User user);
    Boolean updatePassword(String password);
    Boolean updateEmail(String email);
    Boolean sendPasswordResetEmail(String email);
}
