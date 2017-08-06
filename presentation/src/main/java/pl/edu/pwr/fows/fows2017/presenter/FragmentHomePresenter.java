package pl.edu.pwr.fows.fows2017.presenter;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.presenter.base.BasePresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;
import pl.edu.pwr.fows.fows2017.view.FragmentHomeView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 03.08.2017.
 */

public class FragmentHomePresenter extends BasePresenter<FragmentHomeView>{

    private final BaseActivityView baseActivityView;

    public FragmentHomePresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        super(factory);
        this.baseActivityView = baseActivityView;
    }

    public void clickItem(String tag) {
        baseActivityView.changeMainFragment(tag);
    }

    @Override
    public void onViewTaken(FragmentHomeView view) {

    }
}
