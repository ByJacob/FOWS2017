package pl.edu.pwr.fows.fows2017.usecase;

import io.reactivex.Observable;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.gateway.FirebaseTokenGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxObservableUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.08.2017.
 */

public class SendFirebaseTokenUseCase extends AbstractRxObservableUseCase<Boolean> {

    private FirebaseTokenGateway gateway;
    private String token;
    private String language;

    public SendFirebaseTokenUseCase(FowsRxTransformerProvider rxTransformer,
                                    FirebaseTokenGateway gateway, String token,
                                    String language) {
        super(rxTransformer);
        this.gateway = gateway;
        this.token = token;
        this.language = language;
    }

    @Override
    protected Observable<Boolean> createObservable() {
        return Observable.fromCallable(() -> gateway.sendToken(token, language));
    }
}
