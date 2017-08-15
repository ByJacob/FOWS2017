package pl.edu.pwr.fows.fows2017.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ExpandableListView;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.activity.BaseActivity;
import pl.edu.pwr.fows.fows2017.adapter.FragmentAgendaAdapter;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentAgendaModule;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;
import pl.edu.pwr.fows.fows2017.presenter.FragmentAgendaPresenter;
import pl.edu.pwr.fows.fows2017.view.FragmentAgendaView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 15.08.2017.
 */

public class FragmentAgenda extends BaseFragment implements FragmentAgendaView {

    @Inject
    FragmentAgendaPresenter presenter;
    private ExpandableListView expandableListView;

    @Override
    protected Integer getLayoutId() {
        return R.layout.fragment_agenda;
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new FragmentAgendaModule()).inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        expandableListView = getView().findViewById(R.id.fragment_agenda);
        presenter.onViewTaken(this);
    }

    @Override
    public void continueLoading() {
        FragmentAgendaAdapter adapter = new FragmentAgendaAdapter(getActivity());
        adapter.setPresenter(presenter);
        expandableListView.setAdapter(adapter);
        Handler handler = new Handler();
        Runnable task = () -> presenter.finishLoading();
        handler.postDelayed(task, 300);
    }

    @Override
    public void scrollToListPosition(Integer position) {
        expandableListView.smoothScrollToPositionFromTop(position-1, 0);
    }
}
