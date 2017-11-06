package pl.edu.pwr.fows.fows2017.login;

import com.google.gson.Gson;

import java.io.IOException;

import pl.edu.pwr.fows.fows2017.base.OkHttpProvider;
import pl.edu.pwr.fows.fows2017.declarationInterface.AuthInterface;
import pl.edu.pwr.fows.fows2017.declarationInterface.DatabaseInterface;
import pl.edu.pwr.fows.fows2017.entity.User;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public class LoginProvider extends OkHttpProvider{

    private final AuthInterface authInterface;
    private DatabaseInterface databaseInterface;

    public LoginProvider(AuthInterface authInterface, DatabaseInterface databaseInterface) {
        this.authInterface = authInterface;
        this.databaseInterface = databaseInterface;
    }

    public User getUser() {
        String token = authInterface.getToken();
        String url = "https://fows-2017.firebaseio.com/users/"+authInterface.getUserUid()+".json?auth=" + token;
        try {
            Gson gson = new Gson();
            return gson.fromJson(run(url), User.class);
        } catch (IOException e) {
            e.printStackTrace();
            return User.Builder.anUser().build();
        }
    }

    public Boolean addUser(String email, String password, String name, String surname, String university, String company) {
        String displayName = name + " " + surname;
        Boolean isSuccess = authInterface.addUserAndLogin(email, password, displayName);
        if(isSuccess)
            databaseInterface.sendUser(authInterface.getUserUid(), name, surname, email, university, company, authInterface.isEmailVerified());
        return isSuccess;
    }

    public Boolean loginUser(String email, String password) {
        Boolean isLoginSuccess = authInterface.login(email, password);
        if(isLoginSuccess)
            databaseInterface.sendUser(authInterface.getUserUid(), authInterface.isEmailVerified());
        return isLoginSuccess;
    }

    public Boolean sendEmailVerification() {
        return authInterface.sendEmailVerification();
    }

    public Boolean updateUser(User user) {
        Boolean isUpdate = authInterface.updateEmail(user.getEmail(), user.getPassword()) && authInterface.updatePassword(user.getPassword())
                && authInterface.updateDisplayName(user.getName() + " " + user.getSurname());
        if(isUpdate)
            databaseInterface.sendUser(authInterface.getUserUid(), user.getName(), user.getSurname(), user.getEmail(),
                    user.getUniversity(), user.getCompany(), user.getVerify());
        return isUpdate;
    }

    public Boolean sendPasswordResetEmail(String email) {
        return authInterface.sendPasswordResetEmail(email);
    }
}
