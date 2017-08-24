package pl.edu.pwr.fows.fows2017.usecase;

import java.util.List;

import io.reactivex.Observable;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.Question;
import pl.edu.pwr.fows.fows2017.gateway.QuestionGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxObservableUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 24.08.2017.
 */

public class SendQuestionnaireUseCase extends AbstractRxObservableUseCase<String> {

    private QuestionGateway gateway;
    private List<Question> questionList;

    public SendQuestionnaireUseCase(FowsRxTransformerProvider rxTransformer,
                                    QuestionGateway gateway, List<Question> questionList) {
        super(rxTransformer);
        this.gateway = gateway;
        this.questionList = questionList;
    }

    @Override
    protected Observable<String> createObservable() {
        return Observable.fromCallable(() -> gateway.sendQuestionnaire(questionList));
    }
}
