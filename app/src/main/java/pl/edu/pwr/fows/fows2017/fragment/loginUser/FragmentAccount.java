package pl.edu.pwr.fows.fows2017.fragment.loginUser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.adapter.FragmentAccountAdapter;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentAccountModule;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;
import pl.edu.pwr.fows.fows2017.presenter.LoginPresenter;
import pl.edu.pwr.fows.fows2017.view.FragmentAccountView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 06.11.2017.
 */

public class FragmentAccount extends BaseFragment implements FragmentAccountView {

    @Inject
    LoginPresenter presenter;
    private RecyclerView recyclerView;

    @Override
    protected Integer getLayoutId() {
        return R.layout.fragment_account;
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new FragmentAccountModule()).inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = getView().findViewById(R.id.fragment_account_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.onViewTakenFragmentAccount(this);
    }

    @Override
    public void continueLoading() {
        FragmentAccountAdapter adapter = new FragmentAccountAdapter(getContext());
        adapter.setPresenter(presenter);
        recyclerView.setAdapter(adapter);

    }
}
