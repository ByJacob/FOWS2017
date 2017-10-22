package pl.edu.pwr.fows.fows2017.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
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
    private FragmentOrganiserPresenter presenter;
    private TabHost tabHost;
    private final int[] scrollTabs = {R.id.fragment_organiser_scroll_tab1,
            R.id.fragment_organiser_scroll_tab2, R.id.fragment_organiser_scroll_tab3,
            R.id.fragment_organiser_scroll_tab4, R.id.fragment_organiser_scroll_tab5};
    private final int[] tabs = {R.id.fragment_organiser_tab1, R.id.fragment_organiser_tab2,
            R.id.fragment_organiser_tab3, R.id.fragment_organiser_tab4, R.id.fragment_organiser_tab5};

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
        for (int i = 0; i < tabs.length && i< presenter.getOrganiserGroupCount() ; i++) {
            String specName = presenter.getNameTab(i, context.getResources().getConfiguration().locale);
            TabHost.TabSpec spec = tabHost.newTabSpec(specName);
            LinearLayout viewContent = tabHost.findViewById(tabs[i]);
            for(int j=0; j<presenter.getOrganiserGroupPeopleCount(i); j++){
                View view = inflater.inflate(R.layout.row_fragment_organiser_item, null);
                OrganiserHolder organiserHolder = new OrganiserHolder(view);
                presenter.configureOrganiserRowView(organiserHolder, i, j, context.getResources().getConfiguration().locale);
                viewContent.addView(view);
            }
            spec.setContent(scrollTabs[i]);
            spec.setIndicator(specName);
            tabHost.addTab(spec);
        }
    }

    private class OrganiserHolder implements FragmentOrganiserRowView {

        private ImageView image;
        private TextView primaryText;
        private TextView subtext;

        public OrganiserHolder(View rowView) {
            image = rowView.findViewById(R.id.row_fragment_organiser_image);
            primaryText = rowView.findViewById(R.id.row_fragment_organiser_ptext);
            subtext = rowView.findViewById(R.id.row_fragment_organiser_subtext);
        }

        @Override
        public void setImage(String url) {
            Picasso.with(context).load(url).into(image);
        }

        @Override
        public void setPrimaryText(String primaryText) {
            this.primaryText.setText(primaryText);
        }

        @Override
        public void setSubtext(String subtext) {
            this.subtext.setText(subtext);
        }
    }
}
