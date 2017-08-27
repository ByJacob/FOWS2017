package pl.edu.pwr.fows.fows2017.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.adapter.FragmentNewsAdapter;
import pl.edu.pwr.fows.fows2017.customViews.CustomRecyclerView;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentNewsModule;
import pl.edu.pwr.fows.fows2017.firebase.LogEvent;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;
import pl.edu.pwr.fows.fows2017.presenter.FragmentNewsPresenter;
import pl.edu.pwr.fows.fows2017.view.FragmentNewsView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.08.2017.
 */

public class FragmentNews extends BaseFragment implements FragmentNewsView {

    @SuppressWarnings("CanBeFinal")
    @Inject
    FragmentNewsPresenter presenter;
    CustomRecyclerView recyclerView;
    @Inject
    LogEvent logEvent;

    @Override
    protected Integer getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new FragmentNewsModule()).inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = getView().findViewById(R.id.fragment_news_recycler_list);
        recyclerView.setMaxVelocityY(10000);
        presenter.onViewTaken(this);
    }

    @Override
    public void continueLoading() {
        FragmentNewsAdapter adapter = new FragmentNewsAdapter(getActivity());
        adapter.setPresenter(presenter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void firebaseLogPost(String postId, Boolean isOpen) {
        if (isOpen)
            logEvent.clickPost(postId);
        else
            logEvent.showPost(postId);
    }
}
