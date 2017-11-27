package pl.edu.pwr.fows.fows2017.presenter.base;

import java.util.HashMap;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;
import pl.edu.pwr.fows.fows2017.view.base.BaseActivityAndFragmentView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 06.08.2017.
 */

public abstract class BasePresenter<T extends BaseActivityAndFragmentView> {

    protected final UseCaseFactory factory;
    protected BaseActivityView baseActivityView;
    protected T view;

    protected BasePresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        this.factory = factory;
        this.baseActivityView = baseActivityView;
    }

    public abstract void onViewTaken(T view);

}
