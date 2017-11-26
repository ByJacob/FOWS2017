package pl.edu.pwr.fows.fows2017.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.ContestQuestion;
import pl.edu.pwr.fows.fows2017.entity.User;
import pl.edu.pwr.fows.fows2017.presenter.base.BasePresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;
import pl.edu.pwr.fows.fows2017.view.FragmentContestView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 26.11.2017.
 */

public class FragmentContestPresenter extends BasePresenter<FragmentContestView> {


    private List<ContestQuestion> contestQuestions;
    private User user;

    public FragmentContestPresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        super(factory, baseActivityView);
    }

    @Override
    public void onViewTaken(FragmentContestView view) {
        this.view = view;
        baseActivityView.enableLoadingBar();
        factory.getUser().execute().subscribe(this::onFetchUserSuccess, this::onFetchUserFail);
    }

    private void onFetchUserSuccess(User user) {
        if(user.getEmail()!=null){
            this.user = user;
            factory.getContestQuestion().execute().subscribe(this::onFetchContestQuestionSuccess);
        } else {
            baseActivityView.showMessage("NOT_LOGIN", true);
            baseActivityView.showPreviousFragment();
        }
    }

    private void onFetchUserFail(Throwable throwable) {

    }

    private void onFetchContestQuestionSuccess(List<ContestQuestion> contestQuestions) {
        this.contestQuestions = contestQuestions;
        view.continueLoading();
        baseActivityView.disableLoadingBar();
        if(!user.getVerify())
            baseActivityView.showMessage("NOT_VERIFIED", false);
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

    public void sendAnswers(HashMap<String, String> sendAnswers) {
        sendAnswers.put("verified", user.getVerify()? "yes" : "no");
        factory.sendContestAnswer(sendAnswers).execute().subscribe(this::onSendContestAnswerSuccess);
    }

    private void onSendContestAnswerSuccess(Boolean aBoolean) {
        if(aBoolean){
            baseActivityView.showMessage("SEND", null);
            baseActivityView.showPreviousFragment();
        }
    }
}
