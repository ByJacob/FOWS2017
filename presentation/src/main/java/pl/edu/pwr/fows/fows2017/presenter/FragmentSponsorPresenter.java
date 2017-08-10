package pl.edu.pwr.fows.fows2017.presenter;

import java.util.List;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.Sponsor;
import pl.edu.pwr.fows.fows2017.presenter.base.BasePresenter;
import pl.edu.pwr.fows.fows2017.view.FragmentSponsorView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 10.08.2017.
 */

public class FragmentSponsorPresenter extends BasePresenter<FragmentSponsorView> {

    private FragmentSponsorView view;

    public FragmentSponsorPresenter(UseCaseFactory factory) {
        super(factory);
    }

    @Override
    public void onViewTaken(FragmentSponsorView view) {
        this.view = view;
        factory.getSponsors().execute().subscribe(this::onSponsorsFetchSuccess);
    }

    private void onSponsorsFetchSuccess(List<List<Sponsor>> sponsors){

    }
}
