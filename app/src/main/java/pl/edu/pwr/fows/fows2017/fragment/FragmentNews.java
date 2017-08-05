package pl.edu.pwr.fows.fows2017.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.adapter.FragmentNewsAdapter;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentNewsModule;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;
import pl.edu.pwr.fows.fows2017.presenter.FragmentNewsPresenter;
import pl.edu.pwr.fows.fows2017.view.FragmentNewsView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.08.2017.
 */

public class FragmentNews extends BaseFragment implements FragmentNewsView{

    @Inject
    FragmentNewsPresenter presenter;
    private ExpandableListView expListView;
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
        presenter.onViewTaken(this);
        expListView = getView().findViewById(R.id.fragment_news_exp_list);
    }

    @Override
    public void disableLoading() {
        FragmentNewsAdapter adapter = new FragmentNewsAdapter(getActivity());
        adapter.setPresenter(presenter);
        expListView.setAdapter(adapter);
    }
}
