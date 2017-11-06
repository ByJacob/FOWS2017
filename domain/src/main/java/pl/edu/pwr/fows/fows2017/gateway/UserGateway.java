package pl.edu.pwr.fows.fows2017.gateway;

import pl.edu.pwr.fows.fows2017.entity.User;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public interface UserGateway {
    Boolean addUserAndLogin(String email, String password, String name, String surname, String university, String company);
    Boolean login(String email, String password);
    User getUser();
    Boolean sendEmailVerification();
    Boolean updateUser(User user);
    Boolean sendPasswordResetEmail(String email);
}
