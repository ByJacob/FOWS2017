package pl.edu.pwr.fows.fows2017.presenter;

import java.util.List;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.Question;
import pl.edu.pwr.fows.fows2017.presenter.base.BasePresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;
import pl.edu.pwr.fows.fows2017.view.FragmentQuestionnaireView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 22.08.2017.
 */

public class FragmentQuestionnairePresenter extends BasePresenter<FragmentQuestionnaireView> {

    private List<Question> questionList;

    public FragmentQuestionnairePresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        super(factory, baseActivityView);
    }

    @Override
    public void onViewTaken(FragmentQuestionnaireView view) {
        baseActivityView.enableLoadingBar();
        factory.getQuestionnaire().execute().subscribe(this::onFetchSuccessQuestionnaire, this::onFetchFailQuestionnaire);
    }

    private void onFetchSuccessQuestionnaire(List<Question> questions) {
        this.questionList = questions;
    }

    private void onFetchFailQuestionnaire(Throwable throwable) {
        throwable.printStackTrace();
    }
}
