package pl.edu.pwr.fows.fows2017.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentHomeModule;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;
import pl.edu.pwr.fows.fows2017.map.DrawerMenuItemMap;
import pl.edu.pwr.fows.fows2017.presenter.FragmentHomePresenter;
import pl.edu.pwr.fows.fows2017.tools.Utils;
import pl.edu.pwr.fows.fows2017.view.FragmentHomeView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 03.08.2017.
 */

public class FragmentHome extends BaseFragment implements FragmentHomeView {

    @SuppressWarnings("CanBeFinal")
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
        presenter.setLocale(getResources().getConfiguration().locale);
        presenter.onViewTaken(this);
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

    @Override
    public void continueLoading() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("menu");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                presenter.refreshMainIcon();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(postListener);
    }

    @Override
    public void displayLogo(String url) {
        if (getView() != null) {
            Handler handler = new Handler();
            ImageView logo = getView().findViewById(R.id.fragment_home_logo_image);
            View parentLogo = getView().findViewById(R.id.fragment_home_logo_image_parent);
            int logoHeight = parentLogo.getHeight();
            Runnable task1 = () -> {
                Animation.AnimationListener listener = new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Picasso.with(getActivity()).load(url).into(logo);
                        TranslateAnimation animation2 = new TranslateAnimation(0, 0, logoHeight * 1.2f, 0);
                        animation2.setDuration(500);
                        parentLogo.startAnimation(animation2);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                };
                TranslateAnimation animation = new TranslateAnimation(0, 0, 0, logoHeight);
                animation.setAnimationListener(listener);
                animation.setDuration(500);
                parentLogo.startAnimation(animation);
            };
            Runnable task2 = () -> presenter.displaySponsor();
            handler.post(task1);
            handler.postDelayed(task2, 5000);
        }

    }

    @Override
    public void displayLecture(String day, String theme, Boolean isNext, Boolean isAgenda) {
        if (getView() != null) {
            Handler handler = new Handler();
            TextView dateView = getView().findViewById(R.id.fragment_home_textview_presentation_time);
            TextView themeView = getView().findViewById(R.id.fragment_home_textview_presentation_theme);
            TextView status = getView().findViewById(R.id.fragment_home_textview_status);
            View parent = getView().findViewById(R.id.fragment_home_presentation);
            if (isNext == null) {
                day = getString(R.string.finish1);
                theme = getString(R.string.finish2);
            }
            if (!isAgenda) {
                day = getString(R.string.notStart2);
                theme = getString(R.string.notStart1);
            }
            String finalDay = day;
            String finalTheme = theme;
            Runnable task = () -> {
                AlphaAnimation animation = new AlphaAnimation(1f, 0f);
                animation.setDuration(200);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        dateView.setText(finalDay);
                        themeView.setText(finalTheme);
                        if (isNext != null)
                            if (isNext)
                                status.setText(getString(R.string.next));
                            else
                                status.setText(getString(R.string.now));
                        AlphaAnimation animation2 = new AlphaAnimation(0f, 1f);
                        animation2.setDuration(200);
                        parent.startAnimation(animation2);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                parent.startAnimation(animation);
            };
            if (!Objects.equals(themeView.getText().toString(), theme)
                    && !Objects.equals(dateView.getText().toString(), day))
                handler.post(task);
            if (isNext != null) {
                Runnable task2 = () -> presenter.displayLecture(!isNext);
                handler.postDelayed(task2, 8000);
            }
        }
    }

    @Override
    public void updateIcon(Integer position, String tag) {
        if (position > 0 && position < 5 && getView() != null) {
            Integer name = DrawerMenuItemMap.getTag(tag);
            Integer icon = DrawerMenuItemMap.getMainIcon(tag);
            TextView textName = null;
            ImageView imageIcon = null;
            ConstraintLayout container = null;
            switch (position) {
                case 1:
                    textName = getView().findViewById(R.id.fragment_home_textView_11);
                    imageIcon = getView().findViewById(R.id.fragment_home_imageView_11);
                    container = getView().findViewById(R.id.fragment_menu_layout_item1);
                    break;
                case 2:
                    textName = getView().findViewById(R.id.fragment_home_textView_12);
                    imageIcon = getView().findViewById(R.id.fragment_home_imageView_12);
                    container = getView().findViewById(R.id.fragment_menu_layout_item2);
                    break;
                case 3:
                    textName = getView().findViewById(R.id.fragment_home_textView_21);
                    imageIcon = getView().findViewById(R.id.fragment_home_imageView_21);
                    container = getView().findViewById(R.id.fragment_menu_layout_item3);
                    break;
                case 4:
                    textName = getView().findViewById(R.id.fragment_home_textView_22);
                    imageIcon = getView().findViewById(R.id.fragment_home_imageView_22);
                    container = getView().findViewById(R.id.fragment_menu_layout_item4);
                    break;
            }
            if (textName != null && imageIcon != null && container != null) {
                if (!Objects.equals(textName.getText().toString(), getString(name))) {
                    textName.setText(name);
                    Picasso.with(getContext()).load(icon).into(imageIcon);
                    container.setTag(tag);
                }
            }
        }
    }
}
