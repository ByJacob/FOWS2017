package pl.edu.pwr.fows.fows2017.di.module;

import android.app.Application;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.edu.pwr.fows.fows2017.declarationInterface.AuthInterface;
import pl.edu.pwr.fows.fows2017.declarationInterface.DatabaseInterface;
import pl.edu.pwr.fows.fows2017.declarationInterface.SharedPreferencesDataInterface;
import pl.edu.pwr.fows.fows2017.facebookPost.FacebookClient;
import pl.edu.pwr.fows.fows2017.firebase.LogEvent;
import pl.edu.pwr.fows.fows2017.firebaseToken.FirebaseTokenClient;
import pl.edu.pwr.fows.fows2017.gateway.FacebookPostGateway;
import pl.edu.pwr.fows.fows2017.gateway.FirebaseTokenGateway;
import pl.edu.pwr.fows.fows2017.gateway.LectureGateway;
import pl.edu.pwr.fows.fows2017.gateway.MenuGateway;
import pl.edu.pwr.fows.fows2017.gateway.OfferUrlGateway;
import pl.edu.pwr.fows.fows2017.gateway.OrganiserGateway;
import pl.edu.pwr.fows.fows2017.gateway.OrganizerGateway;
import pl.edu.pwr.fows.fows2017.gateway.QuestionGateway;
import pl.edu.pwr.fows.fows2017.gateway.QuestionnaireVersionGateway;
import pl.edu.pwr.fows.fows2017.gateway.SponsorGateway;
import pl.edu.pwr.fows.fows2017.gateway.UserGateway;
import pl.edu.pwr.fows.fows2017.lecture.LectureClient;
import pl.edu.pwr.fows.fows2017.login.LoginClient;
import pl.edu.pwr.fows.fows2017.menu.MenuClient;
import pl.edu.pwr.fows.fows2017.offerUrl.OfferUrlClient;
import pl.edu.pwr.fows.fows2017.organiser.OrganiserClient;
import pl.edu.pwr.fows.fows2017.organizer.OrganizerClient;
import pl.edu.pwr.fows.fows2017.questionnaire.QuestionnaireClient;
import pl.edu.pwr.fows.fows2017.sharedPreferencesAPI.SharedPreferencesAPIClient;
import pl.edu.pwr.fows.fows2017.sponsors.SponsorsClient;
import pl.edu.pwr.fows.fows2017.tools.FirebaseAuthAPI;
import pl.edu.pwr.fows.fows2017.tools.FirebaseDatabaseAPI;
import pl.edu.pwr.fows.fows2017.tools.SharedPreferencesAPI;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    DatabaseInterface getDatabase(){
        return new FirebaseDatabaseAPI();
    }

    @Provides
    @Singleton
    AuthInterface getAuth(){
        return new FirebaseAuthAPI();
    }

    @Provides
    @Singleton
    MenuGateway getMenuGateway() {
        return new MenuClient();
    }

    @Provides
    @Singleton
    SharedPreferencesDataInterface getSharedPreferences() {
        return new SharedPreferencesAPI(application);
    }

    @Provides
    @Singleton
    @Named("NetworkGateway")
    FacebookPostGateway getFacebookPostsGateway(SharedPreferencesDataInterface sharedPreferences) {
        return new FacebookClient(sharedPreferences);
    }

    @Provides
    @Singleton
    @Named("LocalGateway")
    FacebookPostGateway getFacebookPostGatewaySharedPref(SharedPreferencesDataInterface sharedPreferences) {
        return new SharedPreferencesAPIClient(sharedPreferences);
    }

    @Provides
    @Singleton
    @Named("NetworkGateway")
    SponsorGateway getSponsorsGateway() {
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
    LectureGateway getLectureGatewaySharedPref(SharedPreferencesDataInterface sharedPreferences) {
        return new SharedPreferencesAPIClient(sharedPreferences);
    }

    @Provides
    @Singleton
    OrganizerGateway getOrganizerGateway() {
        return new OrganizerClient();
    }

    @Provides
    @Singleton
    OrganiserGateway getOrganiserGateway() {
        return new OrganiserClient();
    }

    @Provides
    @Singleton
    OfferUrlGateway getOfferGateway() {
        return new OfferUrlClient();
    }

    @Provides
    @Singleton
    QuestionGateway getQuestionGateway(SharedPreferencesDataInterface sharedPreferences,
                                       DatabaseInterface databaseInterface) {
        return new QuestionnaireClient(sharedPreferences, databaseInterface);
    }

    @Provides
    @Singleton
    @Named("NetworkGateway")
    QuestionnaireVersionGateway getQuestionnaireVersionGateway(SharedPreferencesDataInterface sharedPreferences,
                                                               DatabaseInterface databaseInterface) {
        return new QuestionnaireClient(sharedPreferences, databaseInterface);
    }

    @Provides
    @Singleton
    @Named("LocalGateway")
    QuestionnaireVersionGateway getQuestionnaireVersionGatewaySharedPref(SharedPreferencesDataInterface sharedPreferences) {
        return new SharedPreferencesAPIClient(sharedPreferences);
    }

    @Provides
    @Singleton
    UserGateway getUserGateway(AuthInterface auth, DatabaseInterface databaseInterface){
        return new LoginClient(auth, databaseInterface);
    }

    @Provides
    @Singleton
    FirebaseTokenGateway getFirebaseTokenGateway() {
        return new FirebaseTokenClient();
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

    @Provides
    @Singleton
    public FirebaseAnalytics provideFirebaseAnalytics() {
        return FirebaseAnalytics.getInstance(application);
    }

    @Provides
    @Singleton
    public LogEvent provideLogEvent(FirebaseAnalytics firebaseAnalytics) {
        return new LogEvent(firebaseAnalytics);
    }
}
