package pl.edu.pwr.fows.fows2017.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentHomeModule;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;
import pl.edu.pwr.fows.fows2017.tools.Utils;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 03.08.2017.
 */

public class FragmentHome extends BaseFragment {

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
    }

    private boolean onTouchItemsListener(View view, MotionEvent motionEvent) {
        ConstraintLayout parent = (ConstraintLayout) view;
        View item = Utils.findChildByPosition(parent, motionEvent.getX(), motionEvent.getY());
        if (item!=null) {
            Toast.makeText(getContext(), String.valueOf(item.getId()), Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}
