package pl.edu.pwr.fows.fows2017.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentHomeModule;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;
import pl.edu.pwr.fows.fows2017.presenter.FragmentHomePresenter;
import pl.edu.pwr.fows.fows2017.tools.Utils;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 03.08.2017.
 */

public class FragmentHome extends BaseFragment {

    @Inject
    FragmentHomePresenter presenter;

    private int countEventMove;

    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new FragmentHomeModule()).inject(this);
    }

    protected Integer getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ConstraintLayout items = getView().findViewById(R.id.fragment_menu_layout_items);
        items.setOnTouchListener(this::onTouchItemsListener);
        countEventMove = 0;
    }

    private boolean onTouchItemsListener(View view, MotionEvent motionEvent) {
        ConstraintLayout parent = (ConstraintLayout) view;
        View item = Utils.findChildByPosition(parent, motionEvent.getX(), motionEvent.getY());
        switch (motionEvent.getActionMasked()) {
            case MotionEvent.ACTION_UP:
                if (countEventMove < 2) {
                    if (item != null) {
                        presenter.clickItem(item.getTag().toString());
                    }
                }
                countEventMove = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                countEventMove++;
            case MotionEvent.ACTION_CANCEL:
                countEventMove = 0;
                break;
        }
        return false;
    }

}
