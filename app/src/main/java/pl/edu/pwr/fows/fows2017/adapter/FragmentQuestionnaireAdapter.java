package pl.edu.pwr.fows.fows2017.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.presenter.FragmentQuestionnairePresenter;
import pl.edu.pwr.fows.fows2017.view.row.FragmentQuestionnaireRowView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 23.08.2017.
 */

public class FragmentQuestionnaireAdapter extends RecyclerView.Adapter<FragmentQuestionnaireAdapter.QuestionHolder> {

    private final Integer VIEW_QUESTION = 0;
    private final Integer VIEW_BUTTON = 1;
    private final Context context;
    private final LayoutInflater inflater;
    private FragmentQuestionnairePresenter presenter;

    public FragmentQuestionnaireAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);
    }

    public void setPresenter(FragmentQuestionnairePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public QuestionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType==VIEW_QUESTION) {
            view = inflater.inflate(R.layout.row_fragment_questionnaire_select_base, parent, false);
        } else {
            view = inflater.inflate(R.layout.row_fragment_questionnaire_button, parent, false);
        }
        return new QuestionHolder(view);
    }

    @Override
    public void onBindViewHolder(QuestionHolder holder, int position) {
        if (holder.getItemViewType()==VIEW_QUESTION) {
            holder.configure();
            holder.setViewType(VIEW_QUESTION);
            holder.setInflater(inflater);
            presenter.configureRow(holder, position, context.getResources().getConfiguration().locale);
        } else {
            holder.setViewType(VIEW_BUTTON);
            holder.configureButton();
        }
    }

    @Override
    public int getItemCount() {
        return presenter.getQuestionCount()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position<presenter.getQuestionCount())
            return VIEW_QUESTION;
        else
            return VIEW_BUTTON;
    }

    class QuestionHolder extends RecyclerView.ViewHolder implements FragmentQuestionnaireRowView {

        private TextWatcher onOtherTextChange;
        private TextView question;
        private FlexboxLayout answerLayout;
        private List<RadioButton> answerButtons;
        private LinearLayout otherLayout;
        private RadioButton otherButton;
        private MaterialEditText otherText;
        private LayoutInflater inflater;
        private Integer viewType;

        QuestionHolder(View itemView) {
            super(itemView);
        }

        public void configure(){
            question = itemView.findViewById(R.id.row_fragment_questionnaire_question_text);
            answerLayout = itemView.findViewById(R.id.row_fragment_questionnaire_answer_layout);
            otherLayout = itemView.findViewById(R.id.row_fragment_questionnaire_other_layout);
            otherButton = itemView.findViewById(R.id.row_fragment_questionnaire_other_button);
            otherText = itemView.findViewById(R.id.row_fragment_questionnaire_other_text);
            setOtherTextChangedListener();
            otherText.setOnClickListener(view -> {});
            otherText.addTextChangedListener(this.onOtherTextChange);
            answerButtons = new ArrayList<>();
        }

        public void configureButton(){
            Button button = itemView.findViewById(R.id.row_fragment_questionnaire_button);
            button.setOnClickListener(view -> presenter.setAnswer());
        }

        public void setViewType(Integer viewType) {
            this.viewType = viewType;
        }

        @Override
        public void setType(String typeString) {
            switch (typeString) {
                case "SELECT":
                    answerLayout.setVisibility(View.VISIBLE);
                    otherButton.setVisibility(View.GONE);
                    otherLayout.setVisibility(View.GONE);
                    break;
                case "SELECT_OTHER":
                    answerLayout.setVisibility(View.VISIBLE);
                    otherButton.setVisibility(View.VISIBLE);
                    otherLayout.setVisibility(View.VISIBLE);
                    otherText.setFloatingLabelText(context.getString(R.string.fragment_row_questionnaire_other));
                    otherText.setHint(context.getString(R.string.fragment_row_questionnaire_other));
                    break;
                default:
                    answerLayout.setVisibility(View.GONE);
                    otherButton.setVisibility(View.GONE);
                    otherLayout.setVisibility(View.VISIBLE);
                    otherText.setFloatingLabelText(context.getString(R.string.fragment_row_questionnaire_answer));
                    otherText.setHint(context.getString(R.string.fragment_row_questionnaire_answer));
                    break;
            }
        }

        @Override
        public void setQuestion(String question) {
            this.question.setText(question);
        }

        @Override
        public void setAnswer(List<String> answers, int position) {
            int i = 0;
            answerLayout.removeAllViews();
            answerButtons.clear();
            for (; i < answers.size(); i++) {
                View view = inflater.inflate(R.layout.row_fragment_questionnaire_select_item,
                        otherLayout, false);
                RadioButton button = view.findViewById(R.id.row_fragment_questionnaire_answer_button);
                button.setTag(position + "_" + i);
                button.setText(answers.get(i));
                button.setOnClickListener(this::buttonClickListener);
                answerButtons.add(button);
                answerLayout.addView(view);
            }
            otherButton.setTag(position + "_" + i);
            otherButton.setOnClickListener(this::buttonClickListener);
        }

        @Override
        public void restoreAnswer(String tag, String text) {
            for (int i = 0; i < answerButtons.size(); i++)
                if (Objects.equals(tag, answerButtons.get(i).getTag().toString()))
                    answerButtons.get(i).setChecked(true);
                else
                    answerButtons.get(i).setChecked(false);
            if (Objects.equals(tag, otherButton.getTag().toString()) && text.length()>0) {
                otherButton.setChecked(true);
                otherText.setText(text);
            } else {
                otherButton.setChecked(false);
                otherText.setText("");
            }
        }

        void setInflater(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        void buttonClickListener(View view) {
            if (view.getTag().toString().length() > 0) {
                for (int i = 0; i < answerButtons.size(); i++)
                    if (view.getTag() != answerButtons.get(i).getTag())
                        answerButtons.get(i).setChecked(false);
                if (view.getTag() != otherButton.getTag()) {
                    otherButton.setChecked(false);
                    presenter.saveAnswer(view.getTag().toString(), view.getTag().toString());
                }
            }
        }

        private void setOtherTextChangedListener() {
            final QuestionHolder parent = this;
            onOtherTextChange = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(charSequence.length()>0) {
                        otherButton.setChecked(true);
                        parent.buttonClickListener(otherButton);
                        presenter.saveAnswer(otherText.getText().toString(), otherButton.getTag().toString());
                    } else if (charSequence.length()==0 && i1 > 0){
                        presenter.saveAnswer(otherText.getText().toString(), otherButton.getTag().toString());
                        otherButton.setChecked(false);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            };
        }

    }
}
