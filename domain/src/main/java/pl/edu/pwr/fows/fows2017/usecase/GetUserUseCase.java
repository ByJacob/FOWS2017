package pl.edu.pwr.fows.fows2017.usecase;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.User;
import pl.edu.pwr.fows.fows2017.gateway.UserGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxObservableUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public class GetUserUseCase extends AbstractRxObservableUseCase<User> {

    private UserGateway gateway;

    public GetUserUseCase(FowsRxTransformerProvider rxTransformer, UserGateway gateway) {
        super(rxTransformer);
        this.gateway = gateway;
    }

    @Override
    protected Observable<User> createObservable() {
        return Observable.fromCallable(() -> gateway.getUser());
    }
}
