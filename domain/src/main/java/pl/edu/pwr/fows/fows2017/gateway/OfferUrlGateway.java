package pl.edu.pwr.fows.fows2017.gateway;

import java.util.Locale;

import io.reactivex.Single;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 21.08.2017.
 */

public interface OfferUrlGateway {

    String getOfferUrl(Locale locale);

}
