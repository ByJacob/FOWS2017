package pl.edu.pwr.fows.fows2017.login;

import pl.edu.pwr.fows.fows2017.declarationInterface.AuthInterface;
import pl.edu.pwr.fows.fows2017.entity.User;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public class LoginProvider {

    private final AuthInterface authInterface;

    public LoginProvider(AuthInterface authInterface) {
        this.authInterface = authInterface;
    }

    public User getUser() {
        return User.Builder.anUser().withEmail(authInterface.getUserEmail())
                .withDisplayName(authInterface.getUserDisplayName())
                .withUid(authInterface.getUserUid())
                .withIsVerified(authInterface.isEmailVerified())
                .build();
    }

    public void addUser(String email, String password, String displayName) {
        authInterface.addUserAndLogin(email, password, displayName);
    }

    public void loginUser(String email, String password) {
        authInterface.login(email, password);
    }

    public Boolean sendEmailVerification() {
        return authInterface.sendEmailVerification();
    }

    public Boolean updateUser(User user) {
        return authInterface.updateEmail(user.getEmail(), user.getPassword()) && authInterface.updatePassword(user.getPassword())
                && authInterface.updateDisplayName(user.getDisplayName());
    }

    public void sendPasswordResetEmail(String email) {
        authInterface.sendPasswordResetEmail(email);
    }
}
