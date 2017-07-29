package pl.edu.pwr.fows.fows2017.usecase.base;

import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 29.07.2017.
 */

public abstract class AbstractRxSingleUseCase<T> implements UseCase<Single<T>>{

    private final FowsRxTransformerProvider rxTransformer;

    public AbstractRxSingleUseCase(FowsRxTransformerProvider rxTransformer) {
        this.rxTransformer = rxTransformer;
    }

    @Override
    public Single<T> execute() {
        return createSingle().compose(rxTransformer.getSingleSchedulers());
    }

    protected abstract Single<T> createSingle();
}
