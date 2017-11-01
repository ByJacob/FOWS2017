package pl.edu.pwr.fows.fows2017.usecase;

import java.util.List;

import io.reactivex.Observable;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.OrganiserTeam;
import pl.edu.pwr.fows.fows2017.gateway.OrganiserGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxObservableUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 17.08.2017.
 */

public class OrganisersUseCase extends AbstractRxObservableUseCase<List<OrganiserTeam>> {

    private final OrganiserGateway gateway;

    public OrganisersUseCase(FowsRxTransformerProvider rxTransformer, OrganiserGateway gateway) {
        super(rxTransformer);
        this.gateway = gateway;
    }

    @Override
    protected Observable<List<OrganiserTeam>> createObservable() {
        return Observable.fromCallable(() -> gateway.getOrganisers());
    }
}
