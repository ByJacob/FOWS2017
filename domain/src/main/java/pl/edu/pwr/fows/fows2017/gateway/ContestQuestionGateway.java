package pl.edu.pwr.fows.fows2017.gateway;

import java.util.HashMap;
import java.util.List;

import pl.edu.pwr.fows.fows2017.entity.ContestQuestion;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 26.11.2017.
 */

public interface ContestQuestionGateway {
    List<ContestQuestion> getQuestions();
    boolean sendAnswers(HashMap<String, String> answers);
    boolean isComplete();
}
