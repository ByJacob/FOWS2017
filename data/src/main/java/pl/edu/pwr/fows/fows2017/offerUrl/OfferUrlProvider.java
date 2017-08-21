package pl.edu.pwr.fows.fows2017.offerUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 21.08.2017.
 */

public class OfferUrlProvider {

    private static final Integer PL = 0;
    private static final Integer EN = 1;

    private final List<String> offersUrl = new ArrayList<>();

    public OfferUrlProvider() {
        offersUrl.add(PL, "http://fows.pwr.edu.pl/FoWS2017_oferta%20wsp%C3%B3%C5%82pracy.pdf");
        offersUrl.add(EN, "http://fows.pwr.edu.pl/FoWS2017_partnership%20proposal.pdf");
    }

    public String getOfferUrl(Locale locale) {
        if (Objects.equals(locale.getLanguage(), "pl"))
            return offersUrl.get(PL);
        else
            return offersUrl.get(EN);
    }
}
