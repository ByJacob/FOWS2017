package pl.edu.pwr.fows.fows2017.usecase;

import java.util.List;

import io.reactivex.Observable;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.Question;
import pl.edu.pwr.fows.fows2017.gateway.QuestionGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxObservableUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 22.08.2017.
 */

public class QuestionnaireUseCase extends AbstractRxObservableUseCase<List<Question>> {

    private QuestionGateway gateway;

    public QuestionnaireUseCase(FowsRxTransformerProvider rxTransformer, QuestionGateway gateway) {
        super(rxTransformer);
        this.gateway = gateway;
    }

    @Override
    protected Observable<List<Question>> createObservable() {
        return Observable.fromCallable(gateway::getQuestionnaire);
    }
}
