package pl.edu.pwr.fows.fows2017.usecase;

import io.reactivex.Observable;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.gateway.UserGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxObservableUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 06.11.2017.
 */

public class LogoutUseCase extends AbstractRxObservableUseCase<Boolean> {

    private UserGateway gateway;


    public LogoutUseCase(FowsRxTransformerProvider rxTransformer, UserGateway gateway) {
        super(rxTransformer);
        this.gateway = gateway;
    }

    @Override
    protected Observable<Boolean> createObservable() {
        return Observable.fromCallable(() -> gateway.signOut());
    }
}
