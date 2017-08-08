package pl.edu.pwr.fows.fows2017.usecase;

import java.util.List;

import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.FacebookPost;
import pl.edu.pwr.fows.fows2017.gateway.FacebookPostGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxSingleUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 08.08.2017.
 */

public class FacebookPostsSharedPrefUseCase extends AbstractRxSingleUseCase<List<FacebookPost>> {

    FacebookPostGateway gateway;

    public FacebookPostsSharedPrefUseCase(FowsRxTransformerProvider rxTransformer, FacebookPostGateway gateway) {
        super(rxTransformer);
        this.gateway = gateway;
    }

    @Override
    protected Single<List<FacebookPost>> createSingle() {
        return gateway.getPostsFromMemory();
    }
}