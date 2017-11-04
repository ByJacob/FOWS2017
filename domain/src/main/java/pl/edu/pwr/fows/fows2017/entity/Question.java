package pl.edu.pwr.fows.fows2017.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Awesome Pojo Generator
 */
public class Question {
    @SerializedName("questionPL")
    @Expose
    private String questionPL;
    @SerializedName("answerPL")
    @Expose
    private List<String> answerPL;
    @SerializedName("answerEN")
    @Expose
    private List<String> answerEN;
    @SerializedName("answer")
    @Expose
    private List<String> answer;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("questionEN")
    @Expose
    private String questionEN;
    private String userAnswer;

    public String getQuestionPL() {
        return questionPL;
    }

    public void setQuestionPL(String questionPL) {
        this.questionPL = questionPL;
    }

    public List<String> getAnswerPL() {
        if (answerPL != null)
            return answerPL;
        else
            return new ArrayList<>();
    }

    public void setAnswerPL(List<String> answerPL) {
        this.answerPL = answerPL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestionEN() {
        return questionEN;
    }

    public void setQuestionEN(String questionEN) {
        this.questionEN = questionEN;
    }

    public List<String> getAnswerEN() {
        if (answerEN != null)
            return answerEN;
        else
            return new ArrayList<>();
    }

    public void setAnswerEN(List<String> answerEN) {
        this.answerEN = answerEN;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
        this.answerPL = answer;
        this.answerEN = answer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}