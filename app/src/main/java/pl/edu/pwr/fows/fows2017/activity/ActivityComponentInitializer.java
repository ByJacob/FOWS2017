package pl.edu.pwr.fows.fows2017.activity;

import pl.edu.pwr.fows.fows2017.application.AppComponentInitializer;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.di.component.AppComponent;
import pl.edu.pwr.fows.fows2017.di.component.DaggerActivityComponent;
import pl.edu.pwr.fows.fows2017.di.module.ActivityModule;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 01.08.2017.
 */

public enum ActivityComponentInitializer {
    INSTANCE;

    private ActivityComponent component;

    public void initFowsActivityComponent(BaseActivity activity){
        if(component==null)
            component = DaggerActivityComponent.builder().appComponent(getAppComponent())
                .activityModule(new ActivityModule(activity))
                .build();
    }

    public ActivityComponent getFowsActivityComponent(){
        return component;
    }

    private AppComponent getAppComponent(){
        return AppComponentInitializer.INSTANCE.getFowsApplicationComponent();
    }
}