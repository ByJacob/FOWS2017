package pl.edu.pwr.fows.fows2017.di.module;

import android.app.Activity;
import android.content.Intent;

import dagger.Module;
import dagger.Provides;
import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.di.scope.ActivityScope;
import pl.edu.pwr.fows.fows2017.presenter.DrawerMenuPresenter;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    public Activity provideActivity() {
        return activity;
    }

    @ActivityScope
    @Provides
    public Intent provideIntent(Activity activity) {
        return activity.getIntent();
    }

    @ActivityScope
    @Provides
    DrawerMenuPresenter getDrawerMenuPresenter(UseCaseFactory useCaseFactory) {
        return new DrawerMenuPresenter(useCaseFactory);
    }

}
