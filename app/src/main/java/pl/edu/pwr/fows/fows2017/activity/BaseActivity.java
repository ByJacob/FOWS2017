package pl.edu.pwr.fows.fows2017.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.BuildConfig;
import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.fragment.DrawerMenuFragment;
import pl.edu.pwr.fows.fows2017.fragment.FragmentAgenda;
import pl.edu.pwr.fows.fows2017.fragment.FragmentHome;
import pl.edu.pwr.fows.fows2017.fragment.FragmentNews;
import pl.edu.pwr.fows.fows2017.fragment.FragmentSponsor;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;
import pl.edu.pwr.fows.fows2017.map.DrawerMenuItemMap;
import pl.edu.pwr.fows.fows2017.map.ExceptionMap;
import pl.edu.pwr.fows.fows2017.presenter.DrawerMenuPresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

public class BaseActivity extends AppCompatActivity implements BaseActivityView {

    @SuppressWarnings("CanBeFinal")
    @Inject
    DrawerMenuPresenter menuPresenter;
    @SuppressWarnings("CanBeFinal")
    @Inject
    Activity activity;
    private Toolbar mToolbar;
    private DrawerMenuFragment drawerFragment;
    private Boolean isBlockClickContainerBody;

    @SuppressWarnings("SameReturnValue")
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
        if (getSupportActionBar() != null)
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
                        case "SPONSORS":
                            fragment = new FragmentSponsor();
                            break;
                        case "AGENDA":
                            fragment = new FragmentAgenda();
                            break;
                    }
                }
                if (fragment != null)
                    try {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container_body, fragment, tag).addToBackStack(tag).commit();
                        if (getSupportActionBar() != null && DrawerMenuItemMap.getTag(tag) != null)
                            getSupportActionBar().setTitle("FoWS 2017 - " + getString(DrawerMenuItemMap.getTag(tag)));
                    } catch (IllegalStateException ignores) {
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
        TextView textSnackBar = snackbar.getView()
                .findViewById(android.support.design.R.id.snackbar_text);
        if (isCritic)
            textSnackBar.setTextColor(Color.RED);
        else
            textSnackBar.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    @Override
    public void enableLoadingBar() {
        ProgressBar loading = (ProgressBar) this.findViewById(R.id.loading_progressBar);
        AlphaAnimation animation = new AlphaAnimation(0f, 0.75f);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                loading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation.setDuration(100);
        loading.startAnimation(animation);
    }

    @Override
    public void disableLoadingBar() {
        ProgressBar loading = (ProgressBar) findViewById(R.id.loading_progressBar);
        AlphaAnimation animation = new AlphaAnimation(0.75f, 0f);
        animation.setDuration(300);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                loading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        loading.startAnimation(animation);

    }

    @Override
    public void continueLoading() {
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar, menuPresenter, activity);
        changeMainFragment("HOME");
    }
}
