package pl.edu.pwr.fows.fows2017.presenter.base;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 06.08.2017.
 */

public abstract class BasePresenter<T> {

    protected final UseCaseFactory factory;
    protected final BaseActivityView baseActivityView;
    protected T view;

    protected BasePresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        this.factory = factory;
        this.baseActivityView = baseActivityView;
    }

    public abstract void onViewTaken(T view);



}
