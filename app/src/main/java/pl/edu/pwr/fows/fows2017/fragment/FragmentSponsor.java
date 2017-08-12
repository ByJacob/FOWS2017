package pl.edu.pwr.fows.fows2017.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentSponsorModule;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;
import pl.edu.pwr.fows.fows2017.presenter.FragmentSponsorPresenter;
import pl.edu.pwr.fows.fows2017.view.FragmentSponsorView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 10.08.2017.
 */

public class FragmentSponsor extends BaseFragment implements FragmentSponsorView {

    @SuppressWarnings("CanBeFinal")
    @Inject
    FragmentSponsorPresenter presenter;

    @Override
    protected Integer getLayoutId() {
        return R.layout.fragment_sponsor;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onViewTaken(this);
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new FragmentSponsorModule()).inject(this);
    }

    @Override
    public void disableLoading() {

    }
}
