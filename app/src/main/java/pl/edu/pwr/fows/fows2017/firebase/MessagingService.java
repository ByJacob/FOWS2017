package pl.edu.pwr.fows.fows2017.firebase;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import pl.edu.pwr.fows.fows2017.customViews.MessagingServiceAlertDialog;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 26.08.2017.
 */

public class MessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";

    private LocalBroadcastManager broadcaster;

    @Override
    public void onCreate() {
        broadcaster = LocalBroadcastManager.getInstance(this);
        super.onCreate();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getNotification() != null)
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        if (remoteMessage.getData() != null) {
            Map<String, String> params = remoteMessage.getData();
            JSONObject object = new JSONObject(params);
            Intent intent = new Intent("messageServiceBroadcast");
            try {
                intent.putExtra(MessagingServiceAlertDialog.MESSAGING_SERVICE_TITLE, object.getString("title"));
                intent.putExtra(MessagingServiceAlertDialog.MESSAGING_SERVICE_MESSAGE, object.getString("body"));
                intent.putExtra(MessagingServiceAlertDialog.MESSAGING_SERVICE_FRAGMENT_TAG, object.getString("tag"));
                broadcaster.sendBroadcast(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        super.onMessageReceived(remoteMessage);
    }

    @Override
    public void handleIntent(Intent intent) {
        intent.putExtra(MessagingServiceAlertDialog.MESSAGING_SERVICE_TITLE, intent.getExtras().getString("title"));
        intent.putExtra(MessagingServiceAlertDialog.MESSAGING_SERVICE_MESSAGE, intent.getExtras().getString("body"));
        intent.putExtra(MessagingServiceAlertDialog.MESSAGING_SERVICE_FRAGMENT_TAG, intent.getExtras().getString("tag"));
        super.handleIntent(intent);
    }
}
