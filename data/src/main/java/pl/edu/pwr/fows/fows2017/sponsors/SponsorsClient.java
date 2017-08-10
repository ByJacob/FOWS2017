package pl.edu.pwr.fows.fows2017.sponsors;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import pl.edu.pwr.fows.fows2017.entity.Sponsor;
import pl.edu.pwr.fows.fows2017.gateway.SponsorGateway;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 10.08.2017.
 */

public class SponsorsClient implements SponsorGateway {

    public static final String URL = "http://fows.pwr.edu.pl/images/sponsors/android-loga.php";
    private SponsorsProvider provider;

    @Inject
    public SponsorsClient() {
        this.provider = new SponsorsProvider(URL);
    }

    @Override
    public Observable<List<List<Sponsor>>> getSponsors() {
        return Observable.fromCallable(() -> provider.getSponsors());
    }
}
