package pl.edu.pwr.fows.fows2017.presenter;

import java.util.List;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.Sponsor;
import pl.edu.pwr.fows.fows2017.presenter.base.BasePresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;
import pl.edu.pwr.fows.fows2017.view.FragmentSponsorView;
import pl.edu.pwr.fows.fows2017.view.row.FragmentSponsorRowView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 10.08.2017.
 */

public class FragmentSponsorPresenter extends BasePresenter<FragmentSponsorView> {

    private List<List<Sponsor>> sponsors;

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
        this.sponsors = sponsors;
        view.continueLoading();
    }

    private void onSponsorsFetchFail(Throwable throwable) {
        factory.getSponsorsSharedPref().execute().subscribe(this::onSponsorsFromMemoryFetchSuccess);
    }

    private void onSponsorsFromMemoryFetchSuccess(List<List<Sponsor>> sponsors){
        baseActivityView.disableLoadingBar();
        this.sponsors = sponsors;
        if(sponsors.size()<1){
            baseActivityView.showOnError("NETWORK", true);
        } else {
            baseActivityView.showOnError("NETWORK", false);
        }
        view.continueLoading();
    }

    public int getSponsorsCount() {
        Integer size = 0;
        for(int i=0; i<sponsors.size(); i++)
            size += sponsors.get(i).size();
        return size;
    }

    public void configureRow(FragmentSponsorRowView holder, int position) {
        int position1 = 0;
        int position2 = 0;
        for(int i=sponsors.size()-1; i>=0; i--){
            if(position<sponsors.get(i).size()) {
                position1 = i;
                position2 = position;
                break;
            }
            position -= sponsors.get(i).size();
        }
        holder.setTitle(sponsors.get(position1).get(position2).getName());
        holder.setImage(sponsors.get(position1).get(position2).getUrl());
    }
}
