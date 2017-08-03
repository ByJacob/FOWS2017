package pl.edu.pwr.fows.fows2017.presenter;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 03.08.2017.
 */

public class FragmentHomePresenter {

    private final UseCaseFactory factory;
    private final BaseActivityView baseActivityView;

    public FragmentHomePresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        this.factory = factory;
        this.baseActivityView = baseActivityView;
    }

    public void clickItem(String tag) {
        baseActivityView.changeMainFragment(tag);
    }
}
