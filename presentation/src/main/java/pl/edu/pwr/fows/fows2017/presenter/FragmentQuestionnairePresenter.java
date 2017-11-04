package pl.edu.pwr.fows.fows2017.presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.Question;
import pl.edu.pwr.fows.fows2017.presenter.base.BasePresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;
import pl.edu.pwr.fows.fows2017.view.FragmentQuestionnaireView;
import pl.edu.pwr.fows.fows2017.view.row.FragmentQuestionnaireRowView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 22.08.2017.
 */

public class FragmentQuestionnairePresenter extends BasePresenter<FragmentQuestionnaireView> {

    private List<Question> questionList = new ArrayList<>();

    public FragmentQuestionnairePresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        super(factory, baseActivityView);
    }

    @Override
    public void onViewTaken(FragmentQuestionnaireView view) {
        baseActivityView.enableLoadingBar();
        this.view = view;
        factory.isQuestionnaireDone().execute().subscribe(this::onFetchSuccessQuestionnaireStatus,
                this::onFetchFailQuestionnaire);
    }

    private void onFetchSuccessQuestionnaireStatus(Boolean isDone) {
        if (isDone) {
            baseActivityView.showMessage("QUESTIONNAIRE_DONE", null);
            baseActivityView.showPreviousFragment();
        } else {
            factory.getQuestionnaire().execute().subscribe(this::onFetchSuccessQuestionnaire, this::onFetchFailQuestionnaire);
        }
    }

    private void onFetchSuccessQuestionnaire(List<Question> questions) {
        this.questionList = questions;
        baseActivityView.disableLoadingBar();
        view.continueLoading();
    }

    private void onFetchFailQuestionnaire(Throwable throwable) {
        if (throwable.getMessage().contains("No address")) {
            baseActivityView.showMessage("NETWORK", true);
            baseActivityView.showPreviousFragment();
        }
    }

    public void configureRow(FragmentQuestionnaireRowView holder, int position, Locale locale) {
        holder.setType(questionList.get(position).getType());
        String isRequired = "";
        if (questionList.get(position).getType().contains("SELECT"))
            isRequired = "*";
        if (Objects.equals(locale.getLanguage(), "pl")) {
            holder.setQuestion((position + 1) + isRequired + "." + questionList.get(position).getQuestionPL());
            holder.setAnswer(questionList.get(position).getAnswerPL(), position);
        } else {
            holder.setQuestion((position + 1) + isRequired + "." + questionList.get(position).getQuestionEN());
            holder.setAnswer(questionList.get(position).getAnswerEN(), position);
        }
        String userAnswer = questionList.get(position).getUserAnswer();
        if(userAnswer == null)
            userAnswer = "";
        String tag = userAnswer.split(":")[0];
        if (tag.length() > 2)
            holder.restoreAnswer(tag, userAnswer.replace(tag + ":", ""));
        else
            holder.restoreAnswer("", "");
    }

    public int getQuestionCount() {
        return questionList.size();
    }

    public void saveAnswer(String text, String tag) {
        String answer;
        String[] tagSplit = tag.split("_");
        if (Objects.equals(text, tag))
            answer = questionList.get(Integer.parseInt(tagSplit[0])).getAnswerPL().get(Integer.parseInt(tagSplit[1]));
        else
            answer = text;
        answer = tag + ":" + answer;
        questionList.get(Integer.parseInt(tagSplit[0])).setUserAnswer(answer);
    }

    public void setAnswer() {
        Boolean isComplete = true;
        for (int i = 0; i < questionList.size(); i++) {
            if (questionList.get(i).getType().contains("SELECT"))
                isComplete = questionList.get(i).getUserAnswer().length() > 0;
        }
        if (isComplete)
            factory.sendQuestionnaire(questionList).execute()
                    .subscribe(this::onSendSuccessQuestionnaire, this::onSendFailQuestionnaire);
        else
            baseActivityView.showMessage("INCOMPLETE", false);
    }

    private void onSendFailQuestionnaire(Throwable throwable) {
        if (throwable.getMessage().contains("No address")) {
            baseActivityView.showMessage("NETWORK", true);
        }
    }

    private void onSendSuccessQuestionnaire(Integer responseCode) {
        for(int i=0; i<questionList.size(); i++){
            questionList.get(i).setUserAnswer("");
        }
        baseActivityView.showMessage("SEND", null);
        baseActivityView.showPreviousFragment();
    }
}
