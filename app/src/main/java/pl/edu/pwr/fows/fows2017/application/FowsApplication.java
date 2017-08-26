package pl.edu.pwr.fows.fows2017.application;

import android.app.Application;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

public class FowsApplication extends Application {
    public String CHANNEL_ID = "WirelessGroup";
    public String CHANNEL_NAME = "Wireless Group";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Firebase", "Token: " + FirebaseInstanceId.getInstance().getToken()); //Todo add Tag Manager
        if(Objects.equals(getResources().getConfiguration().locale.getLanguage(), "pl")) {
            FirebaseMessaging.getInstance().subscribeToTopic("WirelessGroupFowsPL");
            Log.d("Firebase", "Subscription WirelessGroupFowsPL");
        }
        else {
            FirebaseMessaging.getInstance().subscribeToTopic("WirelessGroupFowsEN");
            Log.d("Firebase", "Subscription WirelessGroupFowsEN");
        }
        AppComponentInitializer.INSTANCE.initAppComponent(this);
    }
}
