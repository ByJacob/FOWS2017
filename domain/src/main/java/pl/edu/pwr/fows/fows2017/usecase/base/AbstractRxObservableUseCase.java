package pl.edu.pwr.fows.fows2017.usecase.base;

import io.reactivex.Observable;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 08.08.2017.
 */

public abstract class AbstractRxObservableUseCase<T> implements UseCase<Observable<T>>{

    private final FowsRxTransformerProvider rxTransformer;

    public AbstractRxObservableUseCase(FowsRxTransformerProvider rxTransformer) {
        this.rxTransformer = rxTransformer;
    }

    @Override
    public Observable<T> execute() {
        return createObservable().compose(rxTransformer.getObservableSchedulers());
    }

    protected abstract Observable<T> createObservable();
}
