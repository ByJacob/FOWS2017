package pl.edu.pwr.fows.fows2017.application;

import android.app.Application;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

public class FowsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppComponentInitializer.INSTANCE.initAppComponent(this);
    }
}
