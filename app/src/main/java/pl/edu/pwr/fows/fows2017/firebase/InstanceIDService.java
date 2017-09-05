package pl.edu.pwr.fows.fows2017.firebase;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 26.08.2017.
 */

public class InstanceIDService extends FirebaseInstanceIdService {

    private LocalBroadcastManager broadcaster;

    @Override
    public void onCreate() {
        broadcaster = LocalBroadcastManager.getInstance(this);
        super.onCreate();
    }

    private static final String TAG = "FB.InstanceIDService";
    @Override
    public void onTokenRefresh() {
        Log.d(TAG, "Token: " + FirebaseInstanceId.getInstance().getToken());
        Intent intent = new Intent("instanceIdServiceBroadcast");
        broadcaster.sendBroadcast(intent);
        super.onTokenRefresh();
    }
}
