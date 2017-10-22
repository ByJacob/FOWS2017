package pl.edu.pwr.fows.fows2017.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.adapter.FragmentOrganiserAdapter;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentOrganiserModule;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;
import pl.edu.pwr.fows.fows2017.presenter.FragmentOrganiserPresenter;
import pl.edu.pwr.fows.fows2017.view.FragmentOrganiserView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 16.10.2017.
 */

public class FragmentOrganiser extends BaseFragment implements FragmentOrganiserView {

    @Inject
    FragmentOrganiserPresenter presenter;

    @Override
    protected Integer getLayoutId() {
        return R.layout.fragment_organiser;
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new FragmentOrganiserModule()).inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onViewTaken(this);
    }

    @Override
    public void continueLoading() {
        FragmentOrganiserAdapter adapter = new FragmentOrganiserAdapter(getContext());
        adapter.setPresenter(presenter);
        if (getView() != null) {
            adapter.setTabHost(getView().findViewById(R.id.fragment_organiser_tabhost));
            adapter.display();
        }
    }
}
