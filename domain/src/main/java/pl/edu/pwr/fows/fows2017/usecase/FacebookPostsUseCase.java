package pl.edu.pwr.fows.fows2017.usecase;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.FacebookPost;
import pl.edu.pwr.fows.fows2017.gateway.FacebookPostGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxObservableUseCase;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxSingleUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.08.2017.
 */

public class FacebookPostsUseCase extends AbstractRxObservableUseCase<List<FacebookPost>> {

    FacebookPostGateway gateway;

    public FacebookPostsUseCase(FowsRxTransformerProvider rxTransformer, FacebookPostGateway gateway) {
        super(rxTransformer);
        this.gateway = gateway;
    }

    @Override
    protected Observable<List<FacebookPost>> createSingle() {
        return gateway.getPosts();
    }
}
