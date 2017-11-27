package pl.edu.pwr.fows.fows2017.usecase;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.ContestQuestion;
import pl.edu.pwr.fows.fows2017.gateway.ContestQuestionGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxObservableUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 22.08.2017.
 */

public class SendContestAnswerUseCase extends AbstractRxObservableUseCase<Boolean> {

    private ContestQuestionGateway gateway;
    private HashMap<String, String> answer;

    public SendContestAnswerUseCase(FowsRxTransformerProvider rxTransformer, ContestQuestionGateway gateway,
                                    HashMap<String, String> answer) {
        super(rxTransformer);
        this.gateway = gateway;
        this.answer = answer;
    }

    @Override
    protected Observable<Boolean> createObservable() {
        return Observable.fromCallable(() -> gateway.sendAnswers(answer));
    }
}
