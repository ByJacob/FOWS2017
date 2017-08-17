package pl.edu.pwr.fows.fows2017.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.adapter.FragmentContactAdapter;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentContactModule;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;
import pl.edu.pwr.fows.fows2017.presenter.FragmentContactPresenter;
import pl.edu.pwr.fows.fows2017.view.FragmentContactView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 17.08.2017.
 */

public class FragmentContact extends BaseFragment implements FragmentContactView {

    @Inject
    FragmentContactPresenter presenter;

    private RecyclerView recyclerView;

    @Override
    protected Integer getLayoutId() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new FragmentContactModule()).inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = getView().findViewById(R.id.fragment_contact);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        presenter.onViewTaken(this);
    }

    @Override
    public void continueLoading() {
        FragmentContactAdapter adapter = new FragmentContactAdapter(getActivity());
        adapter.setPresenter(presenter);
        recyclerView.setAdapter(adapter);
    }
}
