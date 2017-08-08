package pl.edu.pwr.fows.fows2017.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.BuildConfig;
import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.fragment.DrawerMenuFragment;
import pl.edu.pwr.fows.fows2017.fragment.FragmentHome;
import pl.edu.pwr.fows.fows2017.fragment.FragmentNews;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;
import pl.edu.pwr.fows.fows2017.map.ExceptionMap;
import pl.edu.pwr.fows.fows2017.presenter.DrawerMenuPresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

public class BaseActivity extends AppCompatActivity implements BaseActivityView {

    @Inject
    DrawerMenuPresenter menuPresenter;
    @Inject
    Activity activity;
    private Toolbar mToolbar;
    private DrawerMenuFragment drawerFragment;
    private Boolean isBlockClickContainerBody;

    private Integer getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        isBlockClickContainerBody = false;
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ActivityComponentInitializer.INSTANCE.initFowsActivityComponent(this);
        ActivityComponentInitializer.INSTANCE.getFowsActivityComponent().inject(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (DrawerMenuFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        menuPresenter.onViewTaken(drawerFragment);
        //BaseFragment baseFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag("MAIN");
    }

    @Override
    public void changeMainFragment(String tag) {
        if (!isBlockClickContainerBody) {
            if (!drawerFragment.presenter.getActualFragmentTag().equals(tag)) {
                if (BuildConfig.DEBUG)
                    Toast.makeText(this, tag, Toast.LENGTH_SHORT).show();
                drawerFragment.presenter.setActualFragmentTag(tag);
                BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    switch (tag) {
                        case "HOME":
                            fragment = new FragmentHome();
                            break;
                        case "NEWS":
                            fragment = new FragmentNews();
                            break;
                    }
                }
                if (fragment != null)
                    try {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container_body, fragment, tag).addToBackStack(tag).commit();
                    } catch (IllegalStateException ignores){
                        ignores.printStackTrace();
                    }
            }
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            int index = fm.getBackStackEntryCount() - 2;
            FragmentManager.BackStackEntry backStackEntry = fm.getBackStackEntryAt(index);
            String tag = backStackEntry.getName();
            fm.popBackStack();
            drawerFragment.presenter.setActualFragmentTag(tag);
        } else
            moveTaskToBack(true);
    }

    @Override
    public void blockContainerClick(Boolean isBlock) {
        this.isBlockClickContainerBody = isBlock;
    }

    @Override
    public void showOnError(String tagError, Boolean isCritic) {
        FrameLayout container_body = (FrameLayout) findViewById(R.id.container_body);
        Snackbar snackbar = Snackbar.make(container_body,
                this.getString(ExceptionMap.getTag(tagError)), BaseTransientBottomBar.LENGTH_LONG);
        TextView textSnackbar = snackbar.getView()
                .findViewById(android.support.design.R.id.snackbar_text);
        if (isCritic)
            textSnackbar.setTextColor(Color.RED);
        else
            textSnackbar.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    @Override
    public void disableLoading() {
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar, menuPresenter, activity);
        changeMainFragment("HOME");
    }
}
