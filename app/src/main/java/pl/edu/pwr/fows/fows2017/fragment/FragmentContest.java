package pl.edu.pwr.fows.fows2017.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentContestModule;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;
import pl.edu.pwr.fows.fows2017.presenter.FragmentContestPresenter;
import pl.edu.pwr.fows.fows2017.view.FragmentContestView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 26.11.2017.
 */

public class FragmentContest extends BaseFragment implements FragmentContestView {

    @Inject
    FragmentContestPresenter presenter;
    LinearLayout container;
    @Override
    protected Integer getLayoutId() {
        return R.layout.fragment_contest;
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new FragmentContestModule()).inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        container = getView().findViewById(R.id.fragment_contest_container);
        presenter.onViewTaken(this);
    }

    @Override
    public void continueLoading() {
        addQuestions();
    }

    private void addQuestions() {
        LayoutInflater inflater = LayoutInflater.from(getView().getContext());
        for(int i = 0; i<presenter.getQuestionsSize(); i++){
            View item = inflater.inflate(R.layout.row_fragment_questionnaire_select_base, container, false);
            View disableIt = item.findViewById(R.id.row_fragment_questionnaire_other_layout);
            disableIt.setVisibility(View.GONE);
            TextView questionTextView = item.findViewById(R.id.row_fragment_questionnaire_question_text);
            String question = (i+1) + "." + presenter.getQuestionText(i);
            questionTextView.setText(question);
            FlexboxLayout radioButtonsContainer = item.findViewById(R.id.row_fragment_questionnaire_answer_layout);
            ArrayList<String> answers = presenter.getAnswers(i);
            Collections.shuffle(answers);
            for(int j=0; j<answers.size(); j++){
                View selectItem = inflater.inflate(R.layout.row_fragment_questionnaire_select_item, null);
                RadioButton radioButton = selectItem.findViewById(R.id.row_fragment_questionnaire_answer_button);
                radioButton.setText(answers.get(j));
                radioButtonsContainer.addView(selectItem);
            }
            container.addView(item);
        }
    }
}
