package pl.edu.pwr.fows.fows2017.view;

import pl.edu.pwr.fows.fows2017.view.base.BaseActivityAndFragmentView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 02.08.2017.
 */

public interface FragmentHomeView extends BaseActivityAndFragmentView {

    void displayLogo(String url);

    void displayLecture(String day, String theme, Boolean isNext, Boolean isAgenda);

    void updateIcon(Integer position, String tag);

}
