package pl.edu.pwr.fows.fows2017.di.module;

import android.app.Application;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.edu.pwr.fows.fows2017.facebookPost.FacebookClient;
import pl.edu.pwr.fows.fows2017.gateway.FacebookPostGateway;
import pl.edu.pwr.fows.fows2017.gateway.LectureGateway;
import pl.edu.pwr.fows.fows2017.gateway.MenuGateway;
import pl.edu.pwr.fows.fows2017.gateway.OfferUrlGateway;
import pl.edu.pwr.fows.fows2017.gateway.OrganizerGateway;
import pl.edu.pwr.fows.fows2017.gateway.SponsorGateway;
import pl.edu.pwr.fows.fows2017.lecture.LectureClient;
import pl.edu.pwr.fows.fows2017.menu.MenuClient;
import pl.edu.pwr.fows.fows2017.offerUrl.OfferUrlClient;
import pl.edu.pwr.fows.fows2017.organizer.OrganizerClient;
import pl.edu.pwr.fows.fows2017.sharedPreferencesAPI.SharedPreferencesAPIClient;
import pl.edu.pwr.fows.fows2017.declarationInterface.SharedPreferencesDataInterface;
import pl.edu.pwr.fows.fows2017.sponsors.SponsorsClient;
import pl.edu.pwr.fows.fows2017.tools.SharedPreferencesAPI;

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
    SharedPreferencesDataInterface getSharedPreferences(){
        return new SharedPreferencesAPI(application);
    }

    @Provides
    @Singleton
    @Named("NetworkGateway")
    FacebookPostGateway getFacebookPostsGateway(SharedPreferencesDataInterface sharedPreferences){
        return new FacebookClient(sharedPreferences);
    }

    @Provides
    @Singleton
    @Named("LocalGateway")
    FacebookPostGateway getFacebookPostGatewaySharedPref(SharedPreferencesDataInterface sharedPreferences){
        return new SharedPreferencesAPIClient(sharedPreferences);
    }

    @Provides
    @Singleton
    @Named("NetworkGateway")
    SponsorGateway getSponsorsGateway(){
        return new SponsorsClient();
    }

    @Provides
    @Singleton
    @Named("NetworkGateway")
    LectureGateway getLectureGateway(SharedPreferencesDataInterface sharedPreferences) {
        return new LectureClient(sharedPreferences);
    }

    @Provides
    @Singleton
    @Named("LocalGateway")
    LectureGateway getLectureGatewaySharedPref(SharedPreferencesDataInterface sharedPreferences){
        return new SharedPreferencesAPIClient(sharedPreferences);
    }

    @Provides
    @Singleton
    OrganizerGateway getOrganizerGateway(){
        return new OrganizerClient();
    }

    @Provides
    @Singleton
    OfferUrlGateway getOfferGateway(){
        return new OfferUrlClient();
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
