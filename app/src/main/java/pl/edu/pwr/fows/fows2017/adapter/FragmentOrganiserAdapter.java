package pl.edu.pwr.fows.fows2017.adapter;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.presenter.FragmentOrganiserPresenter;
import pl.edu.pwr.fows.fows2017.view.row.FragmentOrganiserRowView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 22.10.2017.
 */

public class FragmentOrganiserAdapter {

    private final Context context;
    private final LayoutInflater inflater;
    private final int[] scrollTabs = {R.id.fragment_organiser_scroll_tab1,
            R.id.fragment_organiser_scroll_tab2, R.id.fragment_organiser_scroll_tab3,
            R.id.fragment_organiser_scroll_tab4, R.id.fragment_organiser_scroll_tab5};
    private final int[] tabs = {R.id.fragment_organiser_tab1, R.id.fragment_organiser_tab2,
            R.id.fragment_organiser_tab3, R.id.fragment_organiser_tab4, R.id.fragment_organiser_tab5};
    private FragmentOrganiserPresenter presenter;
    private TabHost tabHost;
    private int currentTab;

    public FragmentOrganiserAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    public void setPresenter(FragmentOrganiserPresenter presenter) {
        this.presenter = presenter;
    }

    public void setTabHost(TabHost parent) {
        this.tabHost = parent;
    }

    public void display() {
        tabHost.setup();
        currentTab = 0;
        for (int i = 0; i < tabs.length && i < presenter.getOrganiserGroupCount(); i++) {
            String specName = presenter.getNameTab(i, context.getResources().getConfiguration().locale);
            TabHost.TabSpec spec = tabHost.newTabSpec(specName);
            LinearLayout viewContent = tabHost.findViewById(tabs[i]);
            spec.setContent(scrollTabs[i]);
            configureGestureDetector(tabHost.findViewById(scrollTabs[i]));
            spec.setIndicator(specName);
            tabHost.addTab(spec);
            for (int j = 0; j < presenter.getOrganiserGroupPeopleCount(i); j++) {
                View view = inflater.inflate(R.layout.row_fragment_organiser_item, null);
                ViewGroup.MarginLayoutParams layoutParamsCardView = (ViewGroup.MarginLayoutParams) view
                        .findViewById(R.id.row_fragment_organiser_card_view).getLayoutParams();
                OrganiserHolder organiserHolder = new OrganiserHolder(view);
                organiserHolder.setImageWidth(viewContent.getWidth() - 2 * layoutParamsCardView.leftMargin);
                viewContent.addView(view);
                presenter.configureOrganiserRowView(organiserHolder, i, j, context.getResources().getConfiguration().locale);
            }
        }
        configureAnimationAndScroll();
    }

    private void configureAnimationAndScroll() {
        HorizontalScrollView scrollView = tabHost.findViewById(R.id.fragment_organiser_horizontal_scroll);
        tabHost.setOnTabChangedListener(s -> {
            View currentView = tabHost.getCurrentView();
            if (tabHost.getCurrentTab() > currentTab) {
                currentView.setAnimation(getAnimation(false));
            } else {
                currentView.setAnimation(getAnimation(true));
            }
            currentTab = tabHost.getCurrentTab();
            int toScrollX = tabHost.findViewById(android.R.id.tabs).getWidth()/
                    tabHost.getTabWidget().getTabCount()*currentTab;
            scrollView.smoothScrollTo(toScrollX, 0);
        });
    }

    private Animation getAnimation(boolean isMoveRight) {
        float fromXValue = +1.0f;
        if (isMoveRight)
            fromXValue = -1.0f;
        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, fromXValue,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        animation.setDuration(240);
        animation.setInterpolator(new AccelerateInterpolator());
        return animation;
    }

    private void configureGestureDetector(View view) {
        GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1,
                                   float velocityX, float velocityY) {
                final int SWIPE_MIN_DISTANCE = 120;
                final int SWIPE_MAX_OFF_PATH = 250;
                final int SWIPE_THRESHOLD_VELOCITY = 200;
                try {
                    if (Math.abs(motionEvent.getY() - motionEvent1.getY()) > SWIPE_MAX_OFF_PATH)
                        return false;
                    if (motionEvent.getX() - motionEvent1.getX() > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        switchTabs(false);
                    } else if (motionEvent.getX() - motionEvent1.getX() < -SWIPE_MIN_DISTANCE
                            && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        switchTabs(true);
                    }
                } catch (Exception e) {
                    // nothing
                }
                return false;
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetectorCompat.onTouchEvent(motionEvent);
            }
        });
    }

    private void switchTabs(boolean direction) {
        if (direction) // true = move left
        {
            if (tabHost.getCurrentTab() != 0)
                tabHost.setCurrentTab(tabHost.getCurrentTab() - 1);
        } else // move right
        {
            if (tabHost.getCurrentTab() != (tabHost.getTabWidget()
                    .getTabCount() - 1))
                tabHost.setCurrentTab(tabHost.getCurrentTab() + 1);
        }
    }

    private class OrganiserHolder implements FragmentOrganiserRowView {

        private ImageView image;
        private TextView primaryText;
        private TextView subtext;
        private int imageWidth;

        public OrganiserHolder(View rowView) {
            image = rowView.findViewById(R.id.row_fragment_organiser_image);
            primaryText = rowView.findViewById(R.id.row_fragment_organiser_ptext);
            subtext = rowView.findViewById(R.id.row_fragment_organiser_subtext);
        }

        public void setImageWidth(int imageWidth) {
            this.imageWidth = imageWidth;
        }

        @Override
        public void setImage(String url) {
            Picasso.with(context).load(url).resize(imageWidth, 0).into(image);
        }

        @Override
        public void setPrimaryText(String primaryText) {
            this.primaryText.setText(primaryText);
        }

        @Override
        public void setSubtext(String subtext) {
            if (subtext.length() > 0) {
                this.subtext.setText(subtext);
                this.subtext.setVisibility(View.VISIBLE);
            } else {
                this.subtext.setVisibility(View.GONE);
            }
        }
    }
}
