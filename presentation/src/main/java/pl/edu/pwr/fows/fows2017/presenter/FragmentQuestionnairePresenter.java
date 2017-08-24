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
        factory.getQuestionnaire().execute().subscribe(this::onFetchSuccessQuestionnaire, this::onFetchFailQuestionnaire);
    }

    private void onFetchSuccessQuestionnaire(List<Question> questions) {
        this.questionList = questions;
        baseActivityView.disableLoadingBar();
        view.continueLoading();
    }

    private void onFetchFailQuestionnaire(Throwable throwable) {
        throwable.printStackTrace();
    }

    public void setAnswer() {
        factory.sendQuestionnaire(questionList).execute()
                .subscribe(this::onSendSuccessQuestionnaire, this::onSendFailQuestionnaire);
    }

    private void onSendFailQuestionnaire(Throwable throwable) {
        if (throwable.getMessage().contains("No address")) {
            baseActivityView.showMessage("NETWORK", true);
        }
    }

    private void onSendSuccessQuestionnaire(String s) {
        baseActivityView.showMessage("SEND", null);
    }

    public void configureRow(FragmentQuestionnaireRowView holder, int position, Locale locale) {
        holder.setType(Question.getTypeString(questionList.get(position).getType()));
        if (Objects.equals(locale.getLanguage(), "pl")) {
            holder.setQuestion((position + 1) + "." + questionList.get(position).getQuestionPL());
            holder.setAnswer(questionList.get(position).getAnswersPL(), position);
        } else {
            holder.setQuestion((position + 1) + "." + questionList.get(position).getQuestionEN());
            holder.setAnswer(questionList.get(position).getAnswersEN(), position);
        }
        String userAnswer = questionList.get(position).getUserAnswer();
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
            answer = questionList.get(Integer.parseInt(tagSplit[0])).getAnswersPL().get(Integer.parseInt(tagSplit[1]));
        else
            answer = text;
        answer = tag + ":" + answer;
        questionList.get(Integer.parseInt(tagSplit[0])).setUserAnswer(answer);
    }
}
