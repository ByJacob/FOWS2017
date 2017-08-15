package pl.edu.pwr.fows.fows2017.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.presenter.FragmentSponsorPresenter;
import pl.edu.pwr.fows.fows2017.view.row.FragmentSponsorRowView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 13.08.2017.
 */

public class FragmentSponsorAdapter {

    private static final Integer COLUMN_COUNT = 120;
    private static final Integer SIZE_4_COLUMN_TAKES = COLUMN_COUNT;
    private static final Integer SIZE_3_COLUMN_TAKES = COLUMN_COUNT / 2;
    private static final Integer SIZE_2_COLUMN_TAKES = COLUMN_COUNT / 3;
    private static final Integer SIZE_1_COLUMN_TAKES = COLUMN_COUNT / 4;
    private static final Integer SIZE_0_COLUMN_TAKES = COLUMN_COUNT / 5;
    private static final Integer MAX_CHILDREN = 5;

    private final Context context;
    private final LayoutInflater inflater;
    private LinearLayout parent;
    private FragmentSponsorPresenter presenter;

    public FragmentSponsorAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);
    }

    public void setPresenter(FragmentSponsorPresenter presenter) {
        this.presenter = presenter;
    }

    public void setParent(LinearLayout parent) {
        this.parent = parent;
    }

    public void display() {
        float scale = context.getResources().getDisplayMetrics().density;
        for (int i = presenter.getSponsorsRowCount() - 1; i >= 0; i--) {
            Integer columnStart = 0;
            Integer rowStart = 0;
            GridLayout grid = null;
            Integer columnTakes = 0;
            switch (i) {
                case 4:
                    grid = parent.findViewById(R.id.fragment_news_row_4);
                    columnTakes = SIZE_4_COLUMN_TAKES;
                    break;
                case 3:
                    grid = parent.findViewById(R.id.fragment_news_row_3);
                    columnTakes = SIZE_3_COLUMN_TAKES;
                    break;
                case 2:
                    grid = parent.findViewById(R.id.fragment_news_row_2);
                    columnTakes = SIZE_2_COLUMN_TAKES;
                    break;
                case 1:
                    grid = parent.findViewById(R.id.fragment_news_row_1);
                    columnTakes = SIZE_1_COLUMN_TAKES;
                    break;
                case 0:
                    grid = parent.findViewById(R.id.fragment_news_row_0);
                    columnTakes = SIZE_0_COLUMN_TAKES;
                    break;
            }
            for (int j = presenter.getSponsorsCountInRow(i) - 1; j >= 0 && grid != null; j--) {
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.height = (int) (columnTakes * 1.6 * scale);
                params.width = 0;
                params.columnSpec = GridLayout.spec(columnStart, columnTakes, 1f);
                params.setGravity(Gravity.FILL);
                Integer margin = context.getResources().getDimensionPixelSize(R.dimen.card_padding);
                params.setMargins(margin, margin, margin, margin);
                columnStart += columnTakes;
                params.rowSpec = GridLayout.spec(rowStart);
                if (columnStart >= COLUMN_COUNT) {
                    columnStart = 0;
                    rowStart += 1;
                }
                View view = getView(i, j);
                grid.addView(view, params);

            }

            fixChildren(grid, columnTakes);
        }

    }

    private void fixChildren(GridLayout grid, Integer columnTakes) {
        if (grid.getChildCount() != grid.getRowCount() * (COLUMN_COUNT / columnTakes)) {
            Integer startChild = (grid.getRowCount() - 1) * (COLUMN_COUNT / columnTakes);
            Integer endChild = grid.getChildCount() - 1;
            Integer columnStart = (COLUMN_COUNT - ((startChild - endChild + 1) * columnTakes)) / 2;
            Integer columnSize = grid.getWidth()/COLUMN_COUNT;

            for (int i = 0; i + startChild <= endChild && grid.getRowCount()>1; i++) {
                grid.getChildAt(startChild + i).animate().x(columnStart*columnSize).setDuration(0)
                        .start();
                columnStart += columnTakes;
            }
        }
    }

    private View getView(int row, int position) {
        View view = inflater.inflate(R.layout.row_fragment_sponsor_item, null);
        SponsorHolder holder = new SponsorHolder(view);
        presenter.configureRow(holder, row, position);
        return view;
    }

    private class SponsorHolder implements FragmentSponsorRowView {

        private ImageView logo;

        SponsorHolder(View rowView) {
            logo = rowView.findViewById(R.id.row_fragment_sponsor_logo);
        }

        @Override
        public void setImage(String url) {
            Picasso.with(context).load(url).into(logo);
        }
    }
}
