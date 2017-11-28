package pl.edu.pwr.fows.fows2017.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.flexbox.FlexboxLayout;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.presenter.FragmentSponsorPresenter;
import pl.edu.pwr.fows.fows2017.view.row.FragmentSponsorRowView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 13.08.2017.
 */

public class FragmentSponsorAdapter {

    private static final Float MAX_SIZE = 600f;
    private static final Float SIZE_0_COLUMN_TAKES = 600f / MAX_SIZE;
    private static final Float SIZE_1_COLUMN_TAKES = 500f / MAX_SIZE;
    private static final Float SIZE_2_COLUMN_TAKES = 300f / MAX_SIZE;
    private static final Float SIZE_4_COLUMN_TAKES = 200f / MAX_SIZE;
    private static final Float SIZE_6_COLUMN_TAKES = 100f / MAX_SIZE;
    private static final Float SIZE_7_COLUMN_TAKES = 200f / MAX_SIZE;
    private static final Float SIZE_8_COLUMN_TAKES = 100f / MAX_SIZE;
    private static final Float SIZE_10_COLUMN_TAKES = 100f / MAX_SIZE;
    private static final Float SIZE_12_COLUMN_TAKES = 100f / MAX_SIZE;

    private final Context context;
    private final LayoutInflater inflater;
    private final Integer width;
    private LinearLayout parent;
    private FragmentSponsorPresenter presenter;

    public FragmentSponsorAdapter(Context context, int x) {
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);
        this.width = x;
    }

    public void setPresenter(FragmentSponsorPresenter presenter) {
        this.presenter = presenter;
    }

    public void setParent(LinearLayout parent) {
        this.parent = parent;
    }

    public void display() {
        for (int i = presenter.getSponsorsRowCount() - 1; i >= 0; i--) {
            FlexboxLayout flex = null;
            Float scale = 0f;
            switch (i) {
                case 0:
                    flex = parent.findViewById(R.id.fragment_news_row_0);
                    scale = SIZE_0_COLUMN_TAKES;
                    break;
                case 1:
                    flex = parent.findViewById(R.id.fragment_news_row_1);
                    scale = SIZE_1_COLUMN_TAKES;
                    break;
                case 2:
                    flex = parent.findViewById(R.id.fragment_news_row_2);
                    scale = SIZE_2_COLUMN_TAKES;
                    break;
                case 3:
                    flex = parent.findViewById(R.id.fragment_news_row_4);
                    scale = SIZE_4_COLUMN_TAKES;
                    break;
                case 4:
                    flex = parent.findViewById(R.id.fragment_news_row_6);
                    scale = SIZE_6_COLUMN_TAKES;
                    break;
                case 5:
                    flex = parent.findViewById(R.id.fragment_news_row_7);
                    scale = SIZE_7_COLUMN_TAKES;
                    break;
                case 6:
                    flex = parent.findViewById(R.id.fragment_news_row_8);
                    scale = SIZE_8_COLUMN_TAKES;
                    break;
                case 7:
                    flex = parent.findViewById(R.id.fragment_news_row_10);
                    scale = SIZE_10_COLUMN_TAKES;
                    break;
                case 8:
                    flex = parent.findViewById(R.id.fragment_news_row_12);
                    scale = SIZE_12_COLUMN_TAKES;
                    break;
            }
            if (!presenter.getIsNetwork()) {
                flex = parent.findViewById(R.id.fragment_news_row_4);
                scale = SIZE_4_COLUMN_TAKES;
            }
            for (int j = presenter.getSponsorsCountInRow(i) - 1; j >= 0 && flex != null; j--) {
                CardView view = getView(i, j, flex, width * scale);
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.width = Math.round(width * scale);
                flex.addView(view, layoutParams);
            }
        }

    }

    private CardView getView(int row, int position, FlexboxLayout flex, float width) {
        CardView view = (CardView) inflater.inflate(R.layout.row_fragment_sponsor_item, flex, false);
        SponsorHolder holder = new SponsorHolder(view, width);
        presenter.configureRow(holder, row, position);
        return view;
    }

    private class SponsorHolder implements FragmentSponsorRowView {

        private ImageView logo;
        private float width;

        SponsorHolder(View rowView, float width) {
            logo = rowView.findViewById(R.id.row_fragment_sponsor_logo);
            this.width = width;
        }

        @Override
        public void setImage(String url) {
            if (Objects.equals(url, "ERROR"))
                logo.setImageResource(R.drawable.no_network);
            else {
                Picasso.with(context).load(url).into(logo);
            }
        }
    }
}
