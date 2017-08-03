package pl.edu.pwr.fows.fows2017.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.BuildConfig;
import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.fragment.DrawerMenuFragment;
import pl.edu.pwr.fows.fows2017.fragment.FragmentHome;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;
import pl.edu.pwr.fows.fows2017.presenter.DrawerMenuPresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

public class BaseActivity extends AppCompatActivity implements BaseActivityView{

    @Inject
    DrawerMenuPresenter menuPresenter;
    private Toolbar mToolbar;
    private DrawerMenuFragment drawerFragment;
    private Boolean isBlockClickContainerBody;

    private Integer getLayoutId(){
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
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar, menuPresenter);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_body, new FragmentHome(), "HOME").commit();
        menuPresenter.setActualFragmentTag("HOME");
        //BaseFragment baseFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag("MAIN");
    }

    @Override
    public void changeMainFragment(String tag) {
        if(!isBlockClickContainerBody) {
            if(BuildConfig.DEBUG)
                Toast.makeText(this, tag, Toast.LENGTH_SHORT).show();
            drawerFragment.presenter.setActualFragmentTag(tag);
        }
    }

    @Override
    public void blockContainerClick(Boolean isBlock) {
        this.isBlockClickContainerBody = isBlock;
    }
}
