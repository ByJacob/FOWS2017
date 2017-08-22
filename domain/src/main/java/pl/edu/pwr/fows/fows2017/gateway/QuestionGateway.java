package pl.edu.pwr.fows.fows2017.gateway;

import java.io.IOException;
import java.util.List;

import pl.edu.pwr.fows.fows2017.entity.Question;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 22.08.2017.
 */

public interface QuestionGateway {
    List<Question> getQuestionnaire() throws IOException;
}
