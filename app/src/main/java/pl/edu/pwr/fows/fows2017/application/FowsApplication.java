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

    public static String TOPIC_PL = "WirelessGroup";
    public static String TOPIC_EN = "Wireless Group";

    @Inject
    LogEvent logEvent;

    @Override
    public void onCreate() {
        super.onCreate();
        AppComponentInitializer.INSTANCE.initAppComponent(this);
        AppComponentInitializer.INSTANCE.getFowsApplicationComponent().inject(this);
        Log.d("Firebase", "Token: " + FirebaseInstanceId.getInstance().getToken()); //Todo add Tag Manager
        if(Objects.equals(getResources().getConfiguration().locale.getLanguage(), "pl")) {
            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC_PL);
            Log.d("Firebase", "Subscription: " + TOPIC_PL);
            logEvent.joinGroup(TOPIC_PL);
        }
        else {
            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC_EN);
            Log.d("Firebase", "Subscription: " + TOPIC_EN);
            logEvent.joinGroup(TOPIC_EN);
        }
    }
}
