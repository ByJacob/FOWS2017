package pl.edu.pwr.fows.fows2017.usecase;

import java.util.Locale;

import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.gateway.OfferUrlGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxSingleUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 21.08.2017.
 */

public class OfferUrlUseCase extends AbstractRxSingleUseCase<String> {

    private OfferUrlGateway gateway;
    private Locale locale;


    public OfferUrlUseCase(FowsRxTransformerProvider rxTransformer, OfferUrlGateway gateway, Locale locale) {
        super(rxTransformer);
        this.locale = locale;
        this.gateway = gateway;
    }

    @Override
    protected Single<String> createSingle() {
        return Single.just(gateway.getOfferUrl(locale));
    }
}
