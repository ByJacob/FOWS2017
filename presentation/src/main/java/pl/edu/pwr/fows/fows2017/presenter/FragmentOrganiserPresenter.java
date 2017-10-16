package pl.edu.pwr.fows.fows2017.presenter;

import java.util.List;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.Organiser;
import pl.edu.pwr.fows.fows2017.presenter.base.BasePresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;
import pl.edu.pwr.fows.fows2017.view.FragmentOrganiserView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 16.10.2017.
 */

public class FragmentOrganiserPresenter extends BasePresenter<FragmentOrganiserView> {

    private List<Organiser> organisers;

    public FragmentOrganiserPresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        super(factory, baseActivityView);
    }

    @Override
    public void onViewTaken(FragmentOrganiserView view) {
        this.view = view;
        baseActivityView.enableLoadingBar();
        super.factory.getOrganisers().execute().subscribe(this::onOrganisersFetchSuccess,
                this::onOrganisersFetchFail);
    }

    private void onOrganisersFetchSuccess(List<Organiser> organisers) {
        this.organisers = organisers;
        baseActivityView.disableLoadingBar();
        view.continueLoading();
    }

    private void onOrganisersFetchFail(Throwable throwable) {

    }
}
