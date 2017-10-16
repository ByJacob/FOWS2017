package pl.edu.pwr.fows.fows2017.usecase;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.Organiser;
import pl.edu.pwr.fows.fows2017.entity.Organizer;
import pl.edu.pwr.fows.fows2017.gateway.OrganiserGateway;
import pl.edu.pwr.fows.fows2017.gateway.OrganizerGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxObservableUseCase;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxSingleUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 17.08.2017.
 */

public class OrganisersUseCase extends AbstractRxObservableUseCase<List<Organiser>> {

    private final OrganiserGateway gateway;

    public OrganisersUseCase(FowsRxTransformerProvider rxTransformer, OrganiserGateway gateway) {
        super(rxTransformer);
        this.gateway = gateway;
    }

    @Override
    protected Observable<List<Organiser>> createObservable() {
        return Observable.fromCallable(() -> gateway.getOrganisers());
    }
}
