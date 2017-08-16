package pl.edu.pwr.fows.fows2017.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.presenter.FragmentAgendaPresenter;
import pl.edu.pwr.fows.fows2017.view.row.FragmentAgendaRowViewHeader;
import pl.edu.pwr.fows.fows2017.view.row.FragmentAgendaRowViewItem;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 15.08.2017.
 */

public class FragmentAgendaAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private final LayoutInflater inflater;
    private FragmentAgendaPresenter presenter;

    public FragmentAgendaAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setPresenter(FragmentAgendaPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public int getGroupCount() {
        return presenter.getLecturesCount();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null)
            view = inflater.inflate(R.layout.row_fragment_agenda_group, null);
        HeaderHolder holder = new HeaderHolder(view);
        holder.setIndicatorSelected(b);
        presenter.configureRowHeader(holder, i, context.getResources().getConfiguration().locale);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null)
            view = inflater.inflate(R.layout.row_fragment_agenda_item, null);
        ItemHolder holder = new ItemHolder(view);
        presenter.configureRowItem(holder, i, context.getResources().getConfiguration().locale);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    private class HeaderHolder implements FragmentAgendaRowViewHeader {

        private CircleImageView speakerPicture;
        private TextView time;
        private TextView speakerName;
        private TextView company;
        private ImageView indicator;
        private TextView day;
        private ImageView statusImage;
        private ConstraintLayout constraint;
        private TextView theme;

        public HeaderHolder(View parent) {
            speakerPicture = parent.findViewById(R.id.row_fragment_agenda_spekacer_circle_view);
            time = parent.findViewById(R.id.row_fragment_agenda_time_text);
            speakerName = parent.findViewById(R.id.row_fragment_agenda_speaker_name_text);
            company = parent.findViewById(R.id.row_fragment_agenda_company_text);
            indicator = parent.findViewById(R.id.row_fragment_agenda_indicator_image);
            day = parent.findViewById(R.id.row_fragment_agenda_day);
            statusImage = parent.findViewById(R.id.row_fragment_agenda_status_image);
            theme = parent.findViewById(R.id.row_fragment_agenda_theme_text);
            constraint = parent.findViewById(R.id.row_fragment_agenda_constraint);
        }

        @Override
        public void displayDayHeader(String day) {
            if (day.length()>1) {
                this.day.setVisibility(View.VISIBLE);
                this.day.setText(day);
            } else {
                this.day.setVisibility(View.GONE);
            }
        }

        @Override
        public void displayTime(String time) {
            this.time.setText(time);
        }

        @Override
        public void displaySpeaker(String name) {
            this.speakerName.setText(name);
        }

        @Override
        public void displaySpeakerPicture(String urlSpeaker) {
            Picasso.with(context).load(urlSpeaker).into(speakerPicture);
        }

        @Override
        public void displayNameCompany(String name) {
            this.company.setText(name);
        }

        @Override
        public void displayTheme(String theme) {
            this.theme.setText(theme);
        }

        @Override
        public void setStatusLecture(STATUS statusLecture) {
            switch (statusLecture) {
                case BEFORE:
                    statusImage.setVisibility(View.GONE);
                    constraint.setAlpha(1f);
                    break;
                case NOW:
                    statusImage.setVisibility(View.VISIBLE);
                    constraint.setAlpha(1f);
                    break;
                case AFTER:
                    statusImage.setVisibility(View.GONE);
                    constraint.setAlpha(0.5f);
            }
        }

        public void setIndicatorSelected(boolean isExpanded) {
            indicator.setSelected(isExpanded);
        }
    }

    private class ItemHolder implements FragmentAgendaRowViewItem {

        private TextView description;
        private ImageView logo;
        private View parent;

        public ItemHolder(View parent) {
            this.description = parent.findViewById(R.id.row_fragment_agenda_description_text);
            this.logo = parent.findViewById(R.id.row_fragment_agenda_logo_image);
            this.parent = parent;
        }

        @Override
        public void displayDescription(String description) {
            this.description.setText(description);
        }

        @Override
        public void displayLogo(String urlLogo) {
            Picasso.with(context).load(urlLogo).into(logo);
        }

        @Override
        public void setStatusLecture(FragmentAgendaRowViewHeader.STATUS statusLecture) {
            if(statusLecture== FragmentAgendaRowViewHeader.STATUS.AFTER)
                parent.setAlpha(0.5f);
            else
                parent.setAlpha(1f);
        }
    }
}
