package pl.edu.pwr.fows.fows2017.view;

import pl.edu.pwr.fows.fows2017.view.base.BaseActivityAndFragmentView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public interface FragmentCreateAccountView extends BaseActivityAndFragmentView {

    Boolean isAllCorrect();
    Boolean isCorrectPassword();
    Boolean isCorrectEmail();
    String getEmail();
    String getPassword();
    String getName();
    String getSurname();

}
