package pl.edu.pwr.fows.fows2017.sponsors;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import pl.edu.pwr.fows.fows2017.declarationInterface.SharedPreferencesDataInterface;
import pl.edu.pwr.fows.fows2017.entity.Sponsor;
import pl.edu.pwr.fows.fows2017.gateway.SponsorGateway;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 10.08.2017.
 */

public class SponsorsClient implements SponsorGateway {

    private static final String URL = "http://fows.pwr.edu.pl/images/sponsors/android-logs.php";
    private final SponsorsProvider provider;

    @Inject
    public SponsorsClient(SharedPreferencesDataInterface sharedPreferences) {
        this.provider = new SponsorsProvider(URL, sharedPreferences);
    }

    @Override
    public Observable<List<List<Sponsor>>> getSponsors() {
        return Observable.fromCallable(provider::getSponsors);
    }
}
