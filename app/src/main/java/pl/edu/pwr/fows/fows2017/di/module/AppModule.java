package pl.edu.pwr.fows.fows2017.di.module;

import android.app.Application;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.edu.pwr.fows.fows2017.gateway.MenuGateway;
import pl.edu.pwr.fows.fows2017.menu.MenuClient;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    MenuGateway getMenuGateway(){
        return new MenuClient();
    }

    @Provides
    @Singleton
    Application getApplication() {
        return application;
    }

    @Provides
    @Singleton
    @Named("SubscribeOnScheduler")
    public Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Named("ObserveOnScheduler")
    public Scheduler provideAndroidMainThread() {
        return AndroidSchedulers.mainThread();
    }
}
