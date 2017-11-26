package pl.edu.pwr.fows.fows2017.presenter;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.ContestQuestion;
import pl.edu.pwr.fows.fows2017.presenter.base.BasePresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;
import pl.edu.pwr.fows.fows2017.view.FragmentContestView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 26.11.2017.
 */

public class FragmentContestPresenter extends BasePresenter<FragmentContestView> {


    private List<ContestQuestion> contestQuestions;

    public FragmentContestPresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        super(factory, baseActivityView);
    }

    @Override
    public void onViewTaken(FragmentContestView view) {
        this.view = view;
        baseActivityView.enableLoadingBar();
        factory.getContestQuestion().execute().subscribe(this::onFetchContestQuestionSuccess);
    }

    private void onFetchContestQuestionSuccess(List<ContestQuestion> contestQuestions) {
        this.contestQuestions = contestQuestions;
        view.continueLoading();
        baseActivityView.disableLoadingBar();
    }

    public int getQuestionsSize() {
        return contestQuestions.size();
    }

    public String getQuestionText(int position) {
        return contestQuestions.get(position).getQuestionPL();
    }

    public ArrayList<String> getAnswers(int position) {
        return contestQuestions.get(position).getAnswer();
    }
}
