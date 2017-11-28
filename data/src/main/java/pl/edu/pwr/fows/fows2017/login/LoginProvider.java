package pl.edu.pwr.fows.fows2017.login;

import com.google.gson.Gson;

import java.io.IOException;

import pl.edu.pwr.fows.fows2017.base.OkHttpProvider;
import pl.edu.pwr.fows.fows2017.declarationInterface.AuthInterface;
import pl.edu.pwr.fows.fows2017.declarationInterface.DatabaseInterface;
import pl.edu.pwr.fows.fows2017.declarationInterface.SharedPreferencesDataInterface;
import pl.edu.pwr.fows.fows2017.entity.User;
import pl.edu.pwr.fows.fows2017.sharedPreferencesAPI.SharedPreferencesAPIProvider;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public class LoginProvider extends OkHttpProvider{

    private final AuthInterface authInterface;
    private DatabaseInterface databaseInterface;
    private SharedPreferencesDataInterface sharedPreferencesDataInterface;

    public LoginProvider(AuthInterface authInterface, DatabaseInterface databaseInterface, SharedPreferencesDataInterface sharedPreferencesDataInterface) {
        this.authInterface = authInterface;
        this.databaseInterface = databaseInterface;
        this.sharedPreferencesDataInterface = sharedPreferencesDataInterface;
    }

    public User getUser() {
        String token = authInterface.getToken();
        String url = "https://fows-2017.firebaseio.com/users/"+authInterface.getUserEmail().replace(".","")+".json?auth=" + token;
        try {
            Gson gson = new Gson();
            return gson.fromJson(run(url), User.class);
        } catch (IOException e) {
            e.printStackTrace();
            return User.Builder.anUser().build();
        }
    }

    public Boolean addUser(String email, String password, String name, String surname, String university, String company) {
        if(university==null)
            university="";
        if(company==null)
            company="";
        String displayName = name + " " + surname;
        Boolean isSuccess = authInterface.addUserAndLogin(email, password, displayName);
        if(isSuccess) {
            databaseInterface.sendUser(authInterface.getUserEmail().replace(".",""), name, surname, university, company, authInterface.isEmailVerified());
            databaseInterface.updateUserEmail(authInterface.getUserEmail().replace(".",""), email);
        }
        return isSuccess;
    }

    public Boolean loginUser(String email, String password) {
        if(email.isEmpty() || password.isEmpty())
            return false;
        Boolean isLoginSuccess = authInterface.login(email, password);
        if(isLoginSuccess) {
            databaseInterface.updateUserVerify(authInterface.getUserEmail().replace(".",""), authInterface.isEmailVerified());
            sharedPreferencesDataInterface.save(SharedPreferencesAPIProvider.TAG_USER, email);
            sharedPreferencesDataInterface.save(SharedPreferencesAPIProvider.TAG_PASSWORD, password);
        }
        return isLoginSuccess;
    }

    public Boolean sendEmailVerification() {
        return authInterface.sendEmailVerification();
    }

    public Boolean updateUser(User user) {
        Boolean isUpdate = authInterface.updateDisplayName(user.getName() + " " + user.getSurname());
        if(isUpdate)
            databaseInterface.sendUser(authInterface.getUserEmail().replace(".",""), user.getName(), user.getSurname(),
                    user.getUniversity(), user.getCompany(), authInterface.isEmailVerified());
        return isUpdate;
    }

    public Boolean sendPasswordResetEmail(String email) {
        return authInterface.sendPasswordResetEmail(email);
    }

    public Boolean loginUser() {
        String user = sharedPreferencesDataInterface.get(SharedPreferencesAPIProvider.TAG_USER, "");
        String password = sharedPreferencesDataInterface.get(SharedPreferencesAPIProvider.TAG_PASSWORD, "");
        if(user.isEmpty() || password.isEmpty())
            return false;
        return loginUser(user, password);
    }

    public Boolean updateUserPassword(String password) {
        Boolean isUpdate = authInterface.updatePassword(password);
        if(isUpdate)
            sharedPreferencesDataInterface.save(SharedPreferencesAPIProvider.TAG_PASSWORD, password);
        return isUpdate;
    }

    public Boolean updateUserEmail(String email) {
        String password = sharedPreferencesDataInterface.get(SharedPreferencesAPIProvider.TAG_PASSWORD, "");
        if(password.isEmpty())
            return false;
        Boolean isUpdate = authInterface.updateEmail(email, password);
        if(isUpdate) {
            databaseInterface.updateUserEmail(authInterface.getUserEmail().replace(".",""), email);
            sharedPreferencesDataInterface.save(SharedPreferencesAPIProvider.TAG_USER, email);
        }
        else
            loginUser();
        return isUpdate;
    }

    public Boolean signOutUser() {
        Boolean isSuccess = authInterface.signOut();
        if(isSuccess){
            sharedPreferencesDataInterface.save(SharedPreferencesAPIProvider.TAG_USER, "");
            sharedPreferencesDataInterface.save(SharedPreferencesAPIProvider.TAG_PASSWORD, "");
        }
        return isSuccess;
    }
}
