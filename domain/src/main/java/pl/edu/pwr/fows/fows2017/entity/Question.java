package pl.edu.pwr.fows.fows2017.entity;

import java.util.List;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 22.08.2017.
 */

public class Question {
    private TYPE type;
    private String questionPL;
    private String questionEN;
    private List<String> answersPL;
    private List<String> answersEN;
    private String userAnswer;

    public Question(TYPE type, String questionPL, String questionEN, List<String> answersPL, List<String> answersEN, String userAnswer) {
        this.type = type;
        this.questionPL = questionPL;
        this.questionEN = questionEN;
        this.answersPL = answersPL;
        this.answersEN = answersEN;
        this.userAnswer = userAnswer;
    }

    public static TYPE getType(String type) {
        switch (type) {
            case "SELECT":
                return TYPE.SELECT;
            case "SELECT_OTHER":
                return TYPE.SELECT_OTHER;
            default:
                return TYPE.OTHER;
        }
    }

    public static String getTypeString(Question.TYPE type) {
        switch (type) {
            case SELECT:
                return "SELECT";
            case SELECT_OTHER:
                return "SELECT_OTHER";
            default:
                return "OTHER";
        }
    }

    public TYPE getType() {
        return type;
    }

    public String getQuestionPL() {
        return questionPL;
    }

    public String getQuestionEN() {
        return questionEN;
    }

    public List<String> getAnswersPL() {
        return answersPL;
    }

    public List<String> getAnswersEN() {
        return answersEN;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public enum TYPE {SELECT, SELECT_OTHER, OTHER}

    public static final class Builder {
        private TYPE type;
        private String questionPL;
        private String questionEN;
        private List<String> answersPL;
        private List<String> answersEN;
        private String userAnswer;

        private Builder() {
            this.userAnswer = "";
        }

        public static Builder aQuestion() {
            return new Builder();
        }

        public Builder withType(TYPE type) {
            this.type = type;
            return this;
        }

        public Builder withQuestionPL(String questionPL) {
            this.questionPL = questionPL;
            return this;
        }

        public Builder withQuestionEN(String questionEN) {
            this.questionEN = questionEN;
            return this;
        }

        public Builder withAnswersPL(List<String> answersPL) {
            this.answersPL = answersPL;
            return this;
        }

        public Builder withAnswersEN(List<String> answersEN) {
            this.answersEN = answersEN;
            return this;
        }

        public Question build() {
            return new Question(type, questionPL, questionEN, answersPL, answersEN, userAnswer);
        }
    }
}
