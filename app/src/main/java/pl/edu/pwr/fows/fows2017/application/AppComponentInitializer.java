package pl.edu.pwr.fows.fows2017.application;

import android.app.Application;

import pl.edu.pwr.fows.fows2017.di.component.DaggerAppComponent;
import pl.edu.pwr.fows.fows2017.di.component.AppComponent;
import pl.edu.pwr.fows.fows2017.di.module.AppModule;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

public enum AppComponentInitializer {
    INSTANCE;

    private AppComponent component;

    public void initAppComponent(Application application){
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(application)).build();
    }

    public AppComponent getFowsApplicationComponent(){
        return component;
    }
}
