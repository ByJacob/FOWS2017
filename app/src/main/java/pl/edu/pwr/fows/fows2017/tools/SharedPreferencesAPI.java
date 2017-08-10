package pl.edu.pwr.fows.fows2017.tools;

import android.content.Context;
import android.content.SharedPreferences;

import pl.edu.pwr.fows.fows2017.sharedPreferencesAPI.SharedPreferencesDataInterface;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 08.08.2017.
 */

public class SharedPreferencesAPI implements SharedPreferencesDataInterface {

    private final String NAME_SHARED_PREF = "FoWS2016";
    private final SharedPreferences sharedPreferences;

    public SharedPreferencesAPI(Context context) {
        this.sharedPreferences = context.getSharedPreferences(NAME_SHARED_PREF, Context.MODE_PRIVATE);
    }

    @Override
    public void save(String TAG, String value) {
        sharedPreferences.edit().putString(TAG, value).apply();
    }

    @Override
    public String get(String TAG, String defaultValue) {
        return sharedPreferences.getString(TAG, defaultValue);
    }
}
