package pl.edu.pwr.fows.fows2017.application;

import android.app.Application;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.di.component.AppComponent;
import pl.edu.pwr.fows.fows2017.firebase.LogEvent;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

public class FowsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppComponentInitializer.INSTANCE.initAppComponent(this);
        AppComponentInitializer.INSTANCE.getFowsApplicationComponent().inject(this);
        Log.d("Firebase", "Token: " + FirebaseInstanceId.getInstance().getToken());
    }
}
