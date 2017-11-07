package pl.edu.pwr.fows.fows2017.tools;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.concurrent.ExecutionException;

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
    public Boolean addUserAndLogin(String email, String password, String displayName) {
        Task<AuthResult> resultTask = auth.createUserWithEmailAndPassword(email, password);
        try {
            Tasks.await(resultTask);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return updateDisplayName(displayName) && sendEmailVerification();
    }

    @Override
    public Boolean login(String email, String password) {
        Task<AuthResult> authResultTask = auth.signInWithEmailAndPassword(email, password);
        try {
            Tasks.await(authResultTask);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean signOut() {
        if(auth.getCurrentUser() != null){
            auth.signOut();
        }
        return true;
    }

    @Override
    public String getToken() {
        if (auth.getCurrentUser() != null) {
            Task<GetTokenResult> idToken = auth.getCurrentUser().getIdToken(false);
            try {
                Tasks.await(idToken);
                return idToken.getResult().getToken();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                return "";
            }
        }
        else
            return "";
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
            Task<Void> voidTask = auth.getCurrentUser().sendEmailVerification();
            try {
                Tasks.await(voidTask);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        } else
            return false;
    }

    @Override
    public Boolean sendPasswordResetEmail(String email) {
        Task<Void> voidTask = auth.sendPasswordResetEmail(email);
        try {
            Tasks.await(voidTask);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean isEmailVerified() {
        return auth.getCurrentUser() != null && auth.getCurrentUser().isEmailVerified();
    }

    @Override
    public Boolean updateEmail(String email, String password) {
        if (auth.getCurrentUser() != null) {
            Task<Void> voidTask = auth.getCurrentUser().updateEmail(email).addOnCompleteListener(task -> reAuth(email, password));
            try {
                Tasks.await(voidTask);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                return false;
            }
            return sendEmailVerification();
        } else
            return false;
    }

    @Override
    public Boolean updatePassword(String password) {
        if (auth.getCurrentUser() != null) {
            String email = getUserEmail();
            Task<Void> voidTask = auth.getCurrentUser().updatePassword(password).addOnCompleteListener(task -> reAuth(email, password));
            try {
                Tasks.await(voidTask);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        } else
            return false;
    }

    @Override
    public Boolean updateDisplayName(String displayName) {
        if (auth.getCurrentUser() != null) {
            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName).build();
            Task<Void> voidTask = auth.getCurrentUser().updateProfile(profileChangeRequest);
            try {
                Tasks.await(voidTask);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        } else
            return false;
    }

    private void reAuth(String email, String password) {
        Task<Void> reauthenticate = auth.getCurrentUser().reauthenticate(EmailAuthProvider.getCredential(email, password));
    }
}
