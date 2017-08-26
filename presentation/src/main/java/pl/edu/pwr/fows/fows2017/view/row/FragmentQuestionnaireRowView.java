package pl.edu.pwr.fows.fows2017.view.row;

import java.util.List;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 23.08.2017.
 */

public interface FragmentQuestionnaireRowView {
    void setType(String typeString);

    void setQuestion(String question);

    void setAnswer(List<String> answers, int position);

    void restoreAnswer(String tag, String text);
}
