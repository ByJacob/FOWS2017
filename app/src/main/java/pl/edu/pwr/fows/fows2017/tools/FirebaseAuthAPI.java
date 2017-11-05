package pl.edu.pwr.fows.fows2017.tools;

import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import pl.edu.pwr.fows.fows2017.declarationInterface.AuthInterface;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public class FirebaseAuthAPI implements AuthInterface {

    private FirebaseAuth auth;

    public FirebaseAuthAPI() {
        this.auth = FirebaseAuth.getInstance();
    }

    @Override
    public void addUserAndLogin(String email, String password, String displayName) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = auth.getCurrentUser();
                UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName).build();
                if (user != null) {
                    user.updateProfile(profileChangeRequest);
                }
                sendEmailVerification();
            }
        });
    }

    @Override
    public void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password);
    }

    @Override
    public String getUserUid() {
        if (auth.getCurrentUser() != null)
            return auth.getCurrentUser().getUid();
        else
            return "";
    }

    @Override
    public String getUserEmail() {
        if (auth.getCurrentUser() != null)
            return auth.getCurrentUser().getEmail();
        else
            return "";
    }

    @Override
    public String getUserDisplayName() {
        if (auth.getCurrentUser() != null)
            return auth.getCurrentUser().getDisplayName();
        else
            return "";
    }

    @Override
    public Boolean sendEmailVerification() {
        if (auth.getCurrentUser() != null) {
            auth.getCurrentUser().sendEmailVerification();
            return true;
        } else
            return false;
    }

    @Override
    public void sendPasswordResetEmail(String email) {
        auth.sendPasswordResetEmail(email);
    }

    @Override
    public Boolean isEmailVerified() {
        return auth.getCurrentUser() != null && auth.getCurrentUser().isEmailVerified();
    }

    @Override
    public Boolean updateEmail(String email, String password) {
        if (auth.getCurrentUser() != null) {
            auth.getCurrentUser().updateEmail(email).addOnCompleteListener(task -> reAuth(email, password));
            return true;
        } else
            return false;
    }

    @Override
    public Boolean updatePassword(String password) {
        if (auth.getCurrentUser() != null) {
            String email = getUserEmail();
            auth.getCurrentUser().updatePassword(password).addOnCompleteListener(task -> reAuth(email, password));
            return true;
        } else
            return false;
    }

    @Override
    public Boolean updateDisplayName(String displayName) {
        if (auth.getCurrentUser() != null) {
            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName).build();
            auth.getCurrentUser().updateProfile(profileChangeRequest);
            return true;
        } else
            return false;
    }

    private void reAuth(String email, String password){
        auth.getCurrentUser().reauthenticate(EmailAuthProvider.getCredential(email, password));
    }
}
