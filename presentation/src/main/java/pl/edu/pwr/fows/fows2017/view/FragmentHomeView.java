package pl.edu.pwr.fows.fows2017.view;

import pl.edu.pwr.fows.fows2017.view.base.BaseActivityAndFragmentView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 02.08.2017.
 */

public interface FragmentHomeView extends BaseActivityAndFragmentView {

    void displayLogo();

    void displayPresentationTime(String string);

    void displayPresentationTheme(String string);

}
