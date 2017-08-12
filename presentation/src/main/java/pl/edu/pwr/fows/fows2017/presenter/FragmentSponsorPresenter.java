package pl.edu.pwr.fows.fows2017.presenter;

import java.util.List;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.Sponsor;
import pl.edu.pwr.fows.fows2017.presenter.base.BasePresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;
import pl.edu.pwr.fows.fows2017.view.FragmentSponsorView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 10.08.2017.
 */

public class FragmentSponsorPresenter extends BasePresenter<FragmentSponsorView> {

    public FragmentSponsorPresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        super(factory, baseActivityView);
    }

    @Override
    public void onViewTaken(FragmentSponsorView view) {
        this.view = view;
        baseActivityView.enableLoadingBar();
        factory.getSponsors().execute().subscribe(this::onSponsorsFetchSuccess, this::onSponsorsFetchFail);
    }

    private void onSponsorsFetchSuccess(List<List<Sponsor>> sponsors){
        baseActivityView.disableLoadingBar();
        view.continueLoading();
    }

    private void onSponsorsFetchFail(Throwable throwable) {
        factory.getSponsorsSharedPref().execute().subscribe(this::onSponsorsFromMemoryFetchSuccess);
    }

    private void onSponsorsFromMemoryFetchSuccess(List<List<Sponsor>> sponsors){
        baseActivityView.disableLoadingBar();
        view.continueLoading();
    }
}
