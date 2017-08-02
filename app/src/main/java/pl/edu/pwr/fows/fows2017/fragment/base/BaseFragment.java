package pl.edu.pwr.fows.fows2017.fragment.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.activity.ActivityComponentInitializer;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.di.component.AppComponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentMainModule;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 02.08.2017.
 */

public class BaseFragment extends Fragment{

    @Inject
    Intent intent;
    private Integer getLayoutId(){
        return R.layout.fragment_main;
    }

    private void performFieldInjection(ActivityComponent activityComponent){
        activityComponent.addModule(new FragmentMainModule()).inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performFieldInjection(getActivityComponent());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(getLayoutId(), container, false);
    }

    protected ActivityComponent getActivityComponent() {
        return ActivityComponentInitializer.INSTANCE.getFowsActivityComponent();
    }
}
