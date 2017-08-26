package pl.edu.pwr.fows.fows2017.usecase;

import java.util.Objects;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.gateway.QuestionnaireVersionGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxObservableUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 26.08.2017.
 */

public class ChechQuestionnaireVersionUseCase extends AbstractRxObservableUseCase<Boolean> {

    private QuestionnaireVersionGateway questionnaireVersionGateway;
    private QuestionnaireVersionGateway questionnaireVersionGatewaySharedPref;

    public ChechQuestionnaireVersionUseCase(FowsRxTransformerProvider rxTransformer,
                                            QuestionnaireVersionGateway questionnaireVersionGateway,
                                            QuestionnaireVersionGateway questionnaireVersionGatewaySharedPref) {
        super(rxTransformer);
        this.questionnaireVersionGateway = questionnaireVersionGateway;
        this.questionnaireVersionGatewaySharedPref = questionnaireVersionGatewaySharedPref;
    }

    @Override
    protected Observable<Boolean> createObservable() {
        return Observable.fromCallable(() -> Objects.equals(questionnaireVersionGateway.getVersion(),
                questionnaireVersionGatewaySharedPref.getVersion()));
    }
}
