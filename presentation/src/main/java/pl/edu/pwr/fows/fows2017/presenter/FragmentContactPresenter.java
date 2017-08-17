package pl.edu.pwr.fows.fows2017.presenter;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.Organizer;
import pl.edu.pwr.fows.fows2017.presenter.base.BasePresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;
import pl.edu.pwr.fows.fows2017.view.FragmentContactView;
import pl.edu.pwr.fows.fows2017.view.row.FragmentContactRowView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 17.08.2017.
 */

public class FragmentContactPresenter extends BasePresenter<FragmentContactView> {

    private List<Organizer> organizers;

    public FragmentContactPresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        super(factory, baseActivityView);
    }

    @Override
    public void onViewTaken(FragmentContactView view) {
        this.view = view;
        baseActivityView.disableLoadingBar();
        factory.getOrganizers().execute().subscribe(this::onFetchOrganizersSuccess);
    }

    private void onFetchOrganizersSuccess(List<Organizer> organizers) {
        this.organizers = organizers;
        view.continueLoading();
    }

    public int getOrganizersCount() {
        return organizers.size();
    }

    public void configureRow(FragmentContactRowView holder, int position, Locale locale) {
        if (Objects.equals(locale.getLanguage(), "pl")) {
            holder.displayRole(organizers.get(position).getRolePL());
        } else {
            holder.displayRole(organizers.get(position).getRoleEN());
        }
        holder.displayName(organizers.get(position).getFirstName() + " " + organizers.get(position).getLastName());
        if (organizers.get(position).getType() == Organizer.TYPE.PERSON) {
            holder.displayPicture(organizers.get(position).getUrlPicture());
            holder.setActionToItem(organizers.get(position).getEmail());
        } else {
            holder.setActionToItem("fb-messenger://user/" + organizers.get(position).getEmail());
            holder.changeIconImage(organizers.get(position).getUrlPicture());
        }
    }
}
