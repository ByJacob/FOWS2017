package pl.edu.pwr.fows.fows2017.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.BuildConfig;
import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.customViews.MessageOffers;
import pl.edu.pwr.fows.fows2017.customViews.MessagingServiceAlertDialog;
import pl.edu.pwr.fows.fows2017.firebase.LogEvent;
import pl.edu.pwr.fows.fows2017.fragment.DrawerMenuFragment;
import pl.edu.pwr.fows.fows2017.fragment.FragmentAgenda;
import pl.edu.pwr.fows.fows2017.fragment.FragmentContact;
import pl.edu.pwr.fows.fows2017.fragment.FragmentCreateAccount;
import pl.edu.pwr.fows.fows2017.fragment.FragmentHome;
import pl.edu.pwr.fows.fows2017.fragment.FragmentLogin;
import pl.edu.pwr.fows.fows2017.fragment.FragmentNews;
import pl.edu.pwr.fows.fows2017.fragment.FragmentOrganiser;
import pl.edu.pwr.fows.fows2017.fragment.FragmentQuestionnaire;
import pl.edu.pwr.fows.fows2017.fragment.FragmentSponsor;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;
import pl.edu.pwr.fows.fows2017.map.DrawerMenuItemMap;
import pl.edu.pwr.fows.fows2017.map.SnackBarMessageMap;
import pl.edu.pwr.fows.fows2017.presenter.DrawerMenuPresenter;
import pl.edu.pwr.fows.fows2017.presenter.LoginPresenter;
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
    LoginPresenter loginPresenter;
    @SuppressWarnings("CanBeFinal")
    @Inject
    Activity activity;
    @SuppressWarnings("CanBeFinal")
    @Inject
    LogEvent logEvent;
    private Toolbar mToolbar;
    private DrawerMenuFragment drawerFragment;
    private Boolean isBlockClickContainerBody;
    private String openFragmentTag;
    private BroadcastReceiver messageServiceBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            MessagingServiceAlertDialog.showAlertDialog(activity, intent, menuPresenter);
        }
    };
    private BroadcastReceiver instanceIdServiceBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkFirebaseToken();
        }
    };

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
        openFragmentTag = "HOME";
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getBoolean("restart")) {
                openFragmentTag = getIntent().getExtras().getString("openTag");
                menuPresenter.setActualFragmentTag("");
            }
        }
        drawerFragment = (DrawerMenuFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        checkFirebaseToken();
        int test = 1;
        if (this.getIntent().getExtras() != null) {
            String string = this.getIntent().getExtras().getString("tag");
            test = 0;
        }
        menuPresenter.onViewTaken(drawerFragment);
        //BaseFragment baseFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag("MAIN");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent.getExtras() != null)
            if (intent.getExtras().containsKey(MessagingServiceAlertDialog.MESSAGING_SERVICE_FRAGMENT_TAG)) {
                Handler handler = new Handler();
                Runnable runnable = () -> MessagingServiceAlertDialog
                        .showAlertDialog(activity, intent, menuPresenter);
                handler.postDelayed(runnable, 0);
            }
        super.onNewIntent(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(messageServiceBroadcast, new IntentFilter("messageServiceBroadcast"));
        LocalBroadcastManager.getInstance(this).registerReceiver(instanceIdServiceBroadcast, new IntentFilter("instanceIdServiceBroadcast"));
    }

    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(messageServiceBroadcast);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(instanceIdServiceBroadcast);
        super.onStop();
    }

    @Override
    public void changeMainFragment(String tag) {
        if (!isBlockClickContainerBody) {
            if (!drawerFragment.presenter.getActualFragmentTag().equals(tag)) {
                if (BuildConfig.DEBUG)
                    Toast.makeText(this, tag, Toast.LENGTH_SHORT).show();
                BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(tag);
                logEvent.openFragment(tag);
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
                        case "CONTACT":
                            fragment = new FragmentContact();
                            break;
                        case "QUESTIONNAIRE":
                            fragment = new FragmentQuestionnaire();
                            break;
                        case "OFFER":
                            menuPresenter.clickOffer(getResources().getConfiguration().locale);
                            break;
                        case "LOCATION":
                            menuPresenter.clickLocation();
                            break;
                        case "ORGANISERS":
                            fragment = new FragmentOrganiser();
                            break;
                        case "CREATE_ACCOUNT":
                            fragment = new FragmentCreateAccount();
                            break;
                        case  "LOGIN":
                            fragment = new FragmentLogin();
                            break;
                    }
                }
                if (fragment != null)
                    try {
                        drawerFragment.closeDrawer();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container_body, fragment, tag).addToBackStack(tag).commit();
                        if (getSupportActionBar() != null && DrawerMenuItemMap.getTag(tag) != null)
                            getSupportActionBar().setTitle("FoWS 2017 - " + getString(DrawerMenuItemMap.getTag(tag)));
                        drawerFragment.presenter.setActualFragmentTag(tag);
                    } catch (IllegalStateException ignores) {
                        ignores.printStackTrace();
                    }
            }
        }
    }

    @Override
    public void showPreviousFragment() {
        onBackPressed();
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
    public void showMessage(String messageTag, Boolean isCritic) {
        FrameLayout container_body = (FrameLayout) findViewById(R.id.container_body);
        Snackbar snackbar = Snackbar.make(container_body,
                this.getString(SnackBarMessageMap.getTag(messageTag)), BaseTransientBottomBar.LENGTH_LONG);
        TextView textSnackBar = snackbar.getView()
                .findViewById(android.support.design.R.id.snackbar_text);
        if (isCritic == null)
            textSnackBar.setTextColor(Color.GREEN);
        else {
            if (isCritic)
                textSnackBar.setTextColor(Color.RED);
            else
                textSnackBar.setTextColor(Color.YELLOW);
        }
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
    public void startBrowser(String offer, String media) {
        MessageOffers.showMessageOffer(this, offer, media, this);
    }

    @Override
    public void startMaps(String place) {
        String url = "https://www.google.com/maps/dir/?api=1&destination=" + place;
        Uri gmmIntentUri = Uri.parse(url);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            showMessage("MAP", true);
        }
    }

    private void checkFirebaseToken() {
        if (FirebaseInstanceId.getInstance().getToken() != null)
            menuPresenter.sendToken(FirebaseInstanceId.getInstance().getToken(), getResources().getConfiguration().locale);
    }

    @Override
    public void sendLogEvent() {
        logEvent.joinGroup(FirebaseInstanceId.getInstance().getToken());
    }

    @Override
    public void continueLoading() {
        drawerFragment.setUp(R.id.fragment_navigation_drawer, findViewById(R.id.drawer_layout),
                mToolbar, menuPresenter, activity, loginPresenter);
        changeMainFragment(openFragmentTag);
    }
}
