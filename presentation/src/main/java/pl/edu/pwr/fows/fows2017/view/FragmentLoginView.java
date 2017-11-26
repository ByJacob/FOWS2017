package pl.edu.pwr.fows.fows2017.view;

import pl.edu.pwr.fows.fows2017.view.base.BaseActivityAndFragmentView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 06.11.2017.
 */

public interface FragmentLoginView extends BaseActivityAndFragmentView {
    Boolean isNoEmpty();
    String getEmail();
    String getPassword();

    void showErrorEmpty();
}
