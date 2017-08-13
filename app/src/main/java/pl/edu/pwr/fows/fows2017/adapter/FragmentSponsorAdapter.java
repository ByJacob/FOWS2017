package pl.edu.pwr.fows.fows2017.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.presenter.FragmentSponsorPresenter;
import pl.edu.pwr.fows.fows2017.view.row.FragmentSponsorRowView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 13.08.2017.
 */

public class FragmentSponsorAdapter extends BaseAdapter {

    private final Context context;
    private final LayoutInflater inflater;
    private FragmentSponsorPresenter presenter;

    public FragmentSponsorAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);
    }

    public void setPresenter(FragmentSponsorPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public int getCount() {
        return presenter.getSponsorsCount();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view==null)
            view = inflater.inflate(R.layout.row_fragment_sponsor_item, null);
        SponsorHolder holder = new SponsorHolder(view);
        presenter.configureRow(holder, position);
        return view;
    }

    private class SponsorHolder implements FragmentSponsorRowView {

        private TextView name;
        private ImageView logo;

        SponsorHolder(View rowView) {
            name = rowView.findViewById(R.id.row_fragment_sponsor_name);
            logo = rowView.findViewById(R.id.row_fragment_sponsor_logo);
        }

        @Override
        public void setTitle(String title) {
            name.setText(title);
        }

        @Override
        public void setImage(String url) {
            Picasso.with(context).load(url).into(logo);
        }
    }
}
