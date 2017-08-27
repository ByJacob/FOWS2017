package pl.edu.pwr.fows.fows2017.firebase;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.08.2017.
 */

public class LogEvent {
    private FirebaseAnalytics firebaseAnalytics;

    public LogEvent(FirebaseAnalytics firebaseAnalytics) {
        this.firebaseAnalytics = firebaseAnalytics;
    }

    public void joinGroup(String groupId){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.GROUP_ID, groupId);
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.JOIN_GROUP, bundle);
    }

    public void openFragment(String tag){
        Bundle bundle = new Bundle();
        bundle.putString("tag", tag);
        firebaseAnalytics.logEvent("fragment_open", bundle);
    }

    public void showPost(String postID){
        Bundle bundle = new Bundle();
        bundle.putString("postId", postID);
        firebaseAnalytics.logEvent("facebook_post", bundle);
    }

    public void clickPost(String postId){
        Bundle bundle = new Bundle();
        bundle.putString("postId", postId);
        firebaseAnalytics.logEvent("facebook_open_post", bundle);
    }
}
