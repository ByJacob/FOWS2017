package pl.edu.pwr.fows.fows2017.presenter;

import java.util.ArrayList;
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

    private List<List<Sponsor>> sponsors = new ArrayList<>();
    private Boolean isNetwork;

    public FragmentSponsorPresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        super(factory, baseActivityView);
    }

    @Override
    public void onViewTaken(FragmentSponsorView view) {
        this.view = view;
        baseActivityView.enableLoadingBar();
        isNetwork=true;
        factory.getSponsors().execute().subscribe(this::onSponsorsFetchSuccess, this::onSponsorsFetchFail);
    }

    private void onSponsorsFetchSuccess(List<List<Sponsor>> sponsors){
        baseActivityView.disableLoadingBar();
        this.sponsors = sponsors;
        view.continueLoading();
    }

    private void onSponsorsFetchFail(Throwable throwable) {
        throwable.printStackTrace();
        if(throwable.getMessage().contains("No address")){
            isNetwork=false;
            baseActivityView.showMessage("NETWORK", true);
            List<Sponsor> error = new ArrayList<>();
            error.add(Sponsor.Builder.aSponsor().withUrl("ERROR").build());
            this.sponsors.add(error);
            view.continueLoading();
        }
        baseActivityView.disableLoadingBar();

    }

    public int getSponsorsRowCount() {
        return sponsors.size();
    }

    public int getSponsorsCountInRow(Integer row) {
        return sponsors.get(row).size();
    }

    public Boolean getIsNetwork(){
        return isNetwork;
    }


    public void configureRow(FragmentSponsorRowView holder, int row, int position) {
        holder.setImage(sponsors.get(row).get(position).getUrl());
    }
}
