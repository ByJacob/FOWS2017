package pl.edu.pwr.fows.fows2017.offerUrl;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.gateway.OfferUrlGateway;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 21.08.2017.
 */

public class OfferUrlClient implements OfferUrlGateway{

    private final OfferUrlProvider provider;

    @Inject
    public OfferUrlClient() {
        this.provider = new OfferUrlProvider();
    }

    @Override
    public String getOfferUrl(Locale locale) {
        return provider.getOfferUrl(locale);
    }
}
