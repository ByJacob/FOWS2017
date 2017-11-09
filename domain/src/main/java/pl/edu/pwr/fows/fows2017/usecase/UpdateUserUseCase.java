package pl.edu.pwr.fows.fows2017.usecase;

import io.reactivex.Observable;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.User;
import pl.edu.pwr.fows.fows2017.gateway.UserGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxObservableUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 06.11.2017.
 */

public class UpdateUserUseCase extends AbstractRxObservableUseCase<Boolean> {

    private UserGateway gateway;
    private User user;


    public UpdateUserUseCase(FowsRxTransformerProvider rxTransformer, UserGateway gateway, User user) {
        super(rxTransformer);
        this.gateway = gateway;
        this.user = user;
    }

    @Override
    protected Observable<Boolean> createObservable() {
        return Observable.fromCallable(() -> gateway.updateUser(user));
    }
}
