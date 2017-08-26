package pl.edu.pwr.fows.fows2017.usecase;

import java.util.List;

import io.reactivex.Observable;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.Sponsor;
import pl.edu.pwr.fows.fows2017.gateway.SponsorGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxObservableUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 10.08.2017.
 */

public class SponsorUseCase extends AbstractRxObservableUseCase<List<List<Sponsor>>> {

    private final SponsorGateway gateway;
    public SponsorUseCase(FowsRxTransformerProvider rxTransformer, SponsorGateway gateway) {
        super(rxTransformer);
        this.gateway = gateway;
    }

    @Override
    protected Observable<List<List<Sponsor>>> createObservable() {
        return Observable.fromCallable(gateway::getSponsors);
    }
}
