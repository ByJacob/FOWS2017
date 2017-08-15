package pl.edu.pwr.fows.fows2017.usecase;

import java.util.List;

import io.reactivex.Observable;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.Lecture;
import pl.edu.pwr.fows.fows2017.gateway.LectureGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxObservableUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 15.08.2017.
 */

public class LecturesUseCase extends AbstractRxObservableUseCase<List<Lecture>> {

    private LectureGateway gateway;

    public LecturesUseCase(FowsRxTransformerProvider rxTransformer, LectureGateway gateway) {
        super(rxTransformer);
        this.gateway = gateway;
    }

    @Override
    protected Observable<List<Lecture>> createSingle() {
        return gateway.getLectures();
    }
}
