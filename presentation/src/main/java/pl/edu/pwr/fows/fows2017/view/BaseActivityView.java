package pl.edu.pwr.fows.fows2017.view;

import pl.edu.pwr.fows.fows2017.view.base.BaseActivityAndFragmentView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 03.08.2017.
 */

public interface BaseActivityView extends BaseActivityAndFragmentView {

    void changeMainFragment(String tag);

    void showPreviousFragment();

    void blockContainerClick(Boolean isBlock);

    void showMessage(String tagError, Boolean isCritic);

    void enableLoadingBar();

    void disableLoadingBar();

    void startBrowser(String url);

    void startMaps(String place);
}
