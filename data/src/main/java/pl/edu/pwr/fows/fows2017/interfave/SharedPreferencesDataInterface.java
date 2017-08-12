package pl.edu.pwr.fows.fows2017.interfave;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 08.08.2017.
 */

public interface SharedPreferencesDataInterface {
    void save(String TAG, String value);
    String get(String TAG, String defaultValue);
}
