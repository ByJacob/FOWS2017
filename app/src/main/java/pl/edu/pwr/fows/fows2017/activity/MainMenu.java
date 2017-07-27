package pl.edu.pwr.fows.fows2017.activity;

import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.activity.base.BaseActivity;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.di.module.MainMenuModule;
import pl.edu.pwr.fows.fows2017.fragment.DrawerMenuFragment;
import pl.edu.pwr.fows.fows2017.presenter.DrawerMenuPresenter;

public class MainMenu extends BaseActivity implements DrawerMenuFragment.FragmentDrawerListener {

    private Toolbar mToolbar;
    private DrawerMenuFragment drawerFragment;

    @Inject
    DrawerMenuPresenter menuPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (DrawerMenuFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar, menuPresenter);
        drawerFragment.setDrawerListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {

    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new MainMenuModule()).inject(this);
    }
}
