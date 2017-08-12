package pl.edu.pwr.fows.fows2017.presenter.base;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 06.08.2017.
 */

public abstract class BasePresenter<T> {

    protected final UseCaseFactory factory;

    protected BasePresenter(UseCaseFactory factory) {
        this.factory = factory;
    }

    public abstract void onViewTaken(T view);

}
