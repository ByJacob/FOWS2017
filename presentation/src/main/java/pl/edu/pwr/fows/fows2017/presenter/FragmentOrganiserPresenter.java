package pl.edu.pwr.fows.fows2017.presenter;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.OrganiserTeam;
import pl.edu.pwr.fows.fows2017.presenter.base.BasePresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;
import pl.edu.pwr.fows.fows2017.view.FragmentOrganiserView;
import pl.edu.pwr.fows.fows2017.view.row.FragmentOrganiserRowView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 16.10.2017.
 */

public class FragmentOrganiserPresenter extends BasePresenter<FragmentOrganiserView> {

    private List<OrganiserTeam> organisers;

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

    private void onOrganisersFetchSuccess(List<OrganiserTeam> organisers) {
        this.organisers = organisers;
        baseActivityView.disableLoadingBar();
        view.continueLoading();
    }

    private void onOrganisersFetchFail(Throwable throwable) {

    }

    public int getOrganiserGroupCount() {
        return organisers.size();
    }

    public int getOrganiserGroupPeopleCount(int group) {
        return organisers.get(group).getPeople().size();
    }

    public String getNameTab(int group, Locale locale) {
        if (Objects.equals(locale.getLanguage(), "pl"))
            return organisers.get(group).getNamePL();
        else
            return organisers.get(group).getNameEN();
    }

    public void configureOrganiserRowView(FragmentOrganiserRowView organiserHolder, int group,
                                          int people, Locale locale) {
        organiserHolder.setImage(organisers.get(group).getPeople().get(people).getPicture());
        organiserHolder.setPrimaryText(organisers.get(group).getPeople().get(people).getPerson());
        if (Objects.equals(locale.getLanguage(), "pl"))
            organiserHolder.setSubtext(organisers.get(group).getPeople().get(people).getNamePL());
        else
            organiserHolder.setSubtext(organisers.get(group).getPeople().get(people).getNameEN());
    }
}
