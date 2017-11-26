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

public class UpdateUserPasswordUseCase extends AbstractRxObservableUseCase<Boolean> {

    private UserGateway gateway;
    private String password;


    public UpdateUserPasswordUseCase(FowsRxTransformerProvider rxTransformer, UserGateway gateway, String password) {
        super(rxTransformer);
        this.gateway = gateway;
        this.password = password;
    }

    @Override
    protected Observable<Boolean> createObservable() {
        return Observable.fromCallable(() -> gateway.updatePassword(password));
    }
}
