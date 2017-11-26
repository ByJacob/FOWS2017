package pl.edu.pwr.fows.fows2017.login;

import pl.edu.pwr.fows.fows2017.declarationInterface.AuthInterface;
import pl.edu.pwr.fows.fows2017.declarationInterface.DatabaseInterface;
import pl.edu.pwr.fows.fows2017.declarationInterface.SharedPreferencesDataInterface;
import pl.edu.pwr.fows.fows2017.entity.User;
import pl.edu.pwr.fows.fows2017.gateway.UserGateway;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public class LoginClient implements UserGateway {

    private LoginProvider provider;

    public LoginClient(AuthInterface authInterface, DatabaseInterface databaseInterface,
                       SharedPreferencesDataInterface sharedPreferencesDataInterface) {
        this.provider = new LoginProvider(authInterface, databaseInterface, sharedPreferencesDataInterface);
    }

    @Override
    public Boolean addUserAndLogin(String email, String password, String name, String surname, String university, String company) {
        return provider.addUser(email, password, name, surname, university, company);
    }

    @Override
    public Boolean login() {
        return provider.loginUser();
    }

    @Override
    public Boolean login(String email, String password) {
        return provider.loginUser(email, password);
    }

    @Override
    public Boolean signOut() {
        return provider.signOutUser();
    }

    @Override
    public User getUser() {
        return provider.getUser();
    }

    @Override
    public Boolean sendEmailVerification() {
        return provider.sendEmailVerification();
    }

    @Override
    public Boolean updateUser(User user) {
        return provider.updateUser(user);
    }

    @Override
    public Boolean updatePassword(String password) {
        return provider.updateUserPassword(password);
    }

    @Override
    public Boolean updateEmail(String email) {
        return provider.updateUserEmail(email);
    }

    @Override
    public Boolean sendPasswordResetEmail(String email) {
        return provider.sendPasswordResetEmail(email);
    }
}
