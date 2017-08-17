package pl.edu.pwr.fows.fows2017.usecase;

import java.util.List;

import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.Organizer;
import pl.edu.pwr.fows.fows2017.gateway.OrganizerGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxSingleUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 17.08.2017.
 */

public class OrganizersUseCase extends AbstractRxSingleUseCase<List<Organizer>>{

    private final OrganizerGateway gateway;

    public OrganizersUseCase(FowsRxTransformerProvider rxTransformer, OrganizerGateway gateway) {
        super(rxTransformer);
        this.gateway = gateway;
    }

    @Override
    protected Single<List<Organizer>> createSingle() {
        return gateway.getOrganizers();
    }
}
