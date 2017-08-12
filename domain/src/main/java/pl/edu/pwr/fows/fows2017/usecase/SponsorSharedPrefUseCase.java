package pl.edu.pwr.fows.fows2017.usecase;

import java.util.List;

import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.Sponsor;
import pl.edu.pwr.fows.fows2017.gateway.SponsorGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxSingleUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 12.08.2017.
 */

public class SponsorSharedPrefUseCase extends AbstractRxSingleUseCase<List<List<Sponsor>>> {

    private final SponsorGateway gateway;

    public SponsorSharedPrefUseCase(FowsRxTransformerProvider rxTransformer, SponsorGateway gateway) {
        super(rxTransformer);
        this.gateway = gateway;
    }

    @Override
    protected Single<List<List<Sponsor>>> createSingle() {
        return gateway.getSponsorsFromMemory();
    }
}
