package pl.edu.pwr.fows.fows2017.entity;

import java.util.List;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 22.08.2017.
 */

public class Question {
    public enum TYPE{SELECT, SELECT_OTHER, OTHER}

    public static TYPE getType(String type){
        switch (type){
            case "SELECT":
                return TYPE.SELECT;
            case "SELECT_OTHER":
                return TYPE.SELECT_OTHER;
            default:
                return TYPE.OTHER;
        }
    }

    private TYPE type;
    private String questionPL;
    private String questionEN;
    private List<String> answersPL;
    private List<String> answersEN;

    public Question(TYPE type, String questionPL, String questionEN, List<String> answersPL, List<String> answersEN) {
        this.type = type;
        this.questionPL = questionPL;
        this.questionEN = questionEN;
        this.answersPL = answersPL;
        this.answersEN = answersEN;
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


    public static final class Builder {
        private TYPE type;
        private String questionPL;
        private String questionEN;
        private List<String> answersPL;
        private List<String> answersEN;

        private Builder() {
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
            return new Question(type, questionPL, questionEN, answersPL, answersEN);
        }
    }
}
