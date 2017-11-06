package pl.edu.pwr.fows.fows2017.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.presenter.LoginPresenter;
import pl.edu.pwr.fows.fows2017.view.row.FragmentAccountRowView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 06.11.2017.
 */

public class FragmentAccountAdapter extends RecyclerView.Adapter<FragmentAccountAdapter.FragmentAccountHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private LoginPresenter presenter;
    private final String[] tags = {"NAME", "SURNAME", "EMAIL", "UNIVERSITY", "COMPANY"};
    private final Integer[] tagsString = {R.string.userName, R.string.userSurname, R.string.emailL,
                                            R.string.userUniversity, R.string.userCompany};

    public FragmentAccountAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public FragmentAccountHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_fragment_account_item, parent, false);
        return new FragmentAccountHolder(view);
    }

    @Override
    public void onBindViewHolder(FragmentAccountHolder holder, int position) {
        presenter.configureRowInAccount(holder, tags[position]);
    }

    @Override
    public int getItemCount() {
        return tags.length;
    }

    public class FragmentAccountHolder extends RecyclerView.ViewHolder implements FragmentAccountRowView {

        private ImageView actionIcon;
        private TextView firstText;
        private TextView secondText;

        public FragmentAccountHolder(View itemView) {
            super(itemView);
            actionIcon = itemView.findViewById(R.id.row_fragment_account_action_icon);
            firstText = itemView.findViewById(R.id.row_fragment_account_text);
            secondText = itemView.findViewById(R.id.row_fragment_account_second_text);
        }

        @Override
        public void setFirstText(String tag) {
            for(int i=0; i<tags.length; i++){
                if(tags[i].equals(tag))
                    firstText.setText(tagsString[i]);
            }
        }

        @Override
        public void setSecondText(String text) {
            secondText.setText(text);
        }
    }
}
