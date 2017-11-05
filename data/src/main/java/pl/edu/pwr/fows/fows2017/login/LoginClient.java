package pl.edu.pwr.fows.fows2017.login;

import pl.edu.pwr.fows.fows2017.declarationInterface.AuthInterface;
import pl.edu.pwr.fows.fows2017.entity.User;
import pl.edu.pwr.fows.fows2017.gateway.UserGateway;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public class LoginClient implements UserGateway {

    private LoginProvider provider;

    public LoginClient(AuthInterface authInterface) {
        this.provider = new LoginProvider(authInterface);
    }

    @Override
    public Boolean addUserAndLogin(String email, String password, String displayName) {
        provider.addUser(email, password, displayName);
        return true;
    }

    @Override
    public Boolean login(String email, String password) {
        provider.loginUser(email, password);
        return true;
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
    public Boolean sendPasswordResetEmail(String email) {
        provider.sendPasswordResetEmail(email);
        return true;
    }
}
