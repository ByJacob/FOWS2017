package pl.edu.pwr.fows.fows2017.di.module;

import android.app.Activity;
import android.content.Intent;

import dagger.Module;
import dagger.Provides;
import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.activity.BaseActivity;
import pl.edu.pwr.fows.fows2017.di.scope.ActivityScope;
import pl.edu.pwr.fows.fows2017.presenter.DrawerMenuPresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

@Module
public class ActivityModule {

    private final BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    BaseActivityView provideBaseActivity(){return activity;}

    @ActivityScope
    @Provides
    Activity provideActivity() {
        return activity;
    }

    @ActivityScope
    @Provides
    Intent provideIntent(Activity activity) {
        return activity.getIntent();
    }

    @ActivityScope
    @Provides
    DrawerMenuPresenter getDrawerMenuPresenter(UseCaseFactory useCaseFactory, BaseActivityView baseActivityView) {
        return new DrawerMenuPresenter(useCaseFactory, baseActivityView);
    }

}
