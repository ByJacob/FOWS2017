package pl.edu.pwr.fows.fows2017.usecase;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.gateway.UserGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxObservableUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public class AddUserAndLoginUseCase extends AbstractRxObservableUseCase<Boolean>{

    private UserGateway gateway;
    private String email;
    private String password;
    private String displayName;

    public AddUserAndLoginUseCase(FowsRxTransformerProvider rxTransformer, UserGateway gateway,
                                  String email, String password, String displayName) {
        super(rxTransformer);
        this.gateway = gateway;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
    }

    @Override
    protected Observable<Boolean> createObservable() {
        return Observable.fromCallable(() -> gateway.addUserAndLogin(email, password, displayName));
    }
}
