package pl.edu.pwr.fows.fows2017.aux_data;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.annotations.NonNull;


/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 29.07.2017.
 */

public final class FowsRxTransformerProvider {

    private final Scheduler subscribeOnScheduler;
    private final Scheduler observerOnScheduler;

    @Inject
    public FowsRxTransformerProvider(@Named("SubscribeOnScheduler")Scheduler subscribeOnScheduler,
                                     @Named("ObserveOnScheduler")Scheduler observerOnScheduler) {
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observerOnScheduler = observerOnScheduler;
    }

    public <T> SingleTransformer<T, T> getSingleSchedulers(){
        return new SingleTransformer<T, T>() {
            @Override
            public SingleSource<T> apply(@NonNull Single<T> upstream) {
                return upstream.observeOn(subscribeOnScheduler).observeOn(observerOnScheduler);
            }
        };
    }
}
