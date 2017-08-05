package pl.edu.pwr.fows.fows2017.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.presenter.FragmentNewsPresenter;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.08.2017.
 */

public class FragmentNewsAdapter extends BaseExpandableListAdapter {

    private FragmentNewsPresenter presenter;
    private Context context;

    public FragmentNewsAdapter(Context context) {
        this.context = context;
    }

    public void setPresenter(FragmentNewsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public int getGroupCount() {
        return presenter.getPostsCount();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return presenter.getPost(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return presenter.getPost(i);
    }

    @Override
    public long getGroupId(int i) {
        return presenter.getPostId(i);
    }

    @Override
    public long getChildId(int i, int i1) {
        return presenter.getPostId(i);
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean isExpanded, View view, ViewGroup parent) {
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_fragment_news_header, null);
        }

        TextView date = view.findViewById(R.id.row_fragment_news_header_text_view);
        date.setText(presenter.getHeaderDate(i, context.getResources().getConfiguration().locale));
        ImageView indicator = view.findViewById(R.id.row_fragment_news_header_indicator);
        indicator.setSelected(isExpanded);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_fragment_news_item, null);
        }

        TextView message = view.findViewById(R.id.row_fragment_news_item_text_view);
        message.setText(presenter.getMessage(i));

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
