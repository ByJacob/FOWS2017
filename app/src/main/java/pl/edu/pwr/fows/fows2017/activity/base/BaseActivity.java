package pl.edu.pwr.fows.fows2017.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import pl.edu.pwr.fows.fows2017.application.AppComponentInitializer;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.di.component.DaggerActivityComponent;
import pl.edu.pwr.fows.fows2017.di.component.AppComponent;
import pl.edu.pwr.fows.fows2017.di.module.ActivityModule;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

public abstract class BaseActivity extends AppCompatActivity{

    private ActivityComponent activityComponent;

    protected abstract void performFieldInjection(ActivityComponent activityComponent);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performFieldInjection(getActivityComponent());
    }

    private AppComponent getAppComponent(){
        return AppComponentInitializer.INSTANCE.getFowsApplicationComponent();
    }
    private ActivityComponent getActivityComponent(){
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder().appComponent(getAppComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return activityComponent;
    }
}
