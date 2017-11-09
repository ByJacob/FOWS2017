package pl.edu.pwr.fows.fows2017.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

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
    private final String[] tags = {"NAME", "SURNAME", "EMAIL", "UNIVERSITY", "COMPANY"};
    private final Integer[] tagsString = {R.string.userNameAccount, R.string.userSurnameAccount, R.string.emailL,
            R.string.userUniversity, R.string.userCompany};
    private LoginPresenter presenter;

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
        return new FragmentAccountHolder(view, inflater);
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
        private LayoutInflater inflater;
        String originalSecondTextString = "";

        public FragmentAccountHolder(View itemView, LayoutInflater inflater) {
            super(itemView);
            actionIcon = itemView.findViewById(R.id.row_fragment_account_action_icon);
            actionIcon.setOnClickListener(this::actionIconClick);
            firstText = itemView.findViewById(R.id.row_fragment_account_text);
            secondText = itemView.findViewById(R.id.row_fragment_account_second_text);
            secondText.setTextColor(context.getResources().getColor(R.color.black));
            this.inflater = inflater;
        }

        private void actionIconClick(View view) {
            final View viewAlert = inflater.inflate(R.layout.row_fragment_account_alert_dialog, null);
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle(R.string.change_info);
            alertDialog.setCancelable(false);

            final MaterialEditText changeEditText = viewAlert.findViewById(R.id.fragment_account_row_alert_dialog_edit_text);
            changeEditText.setHint(firstText.getText().toString());
            changeEditText.setText(secondText.getText().toString());
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, context.getResources().getString(R.string.change),
                    (dialogInterface, i) -> {
                        if (!originalSecondTextString.equals(changeEditText.getText().toString())) {
                            secondText.setText(changeEditText.getText().toString());
                            secondText.setTextColor(context.getResources().getColor(android.R.color.holo_green_dark));
                            presenter.updateUserElement(firstText.getTag().toString(), changeEditText.getText().toString());
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, context.getResources().getString(R.string.cancel),
                    ((dialogInterface, i) -> alertDialog.dismiss()));
            alertDialog.setView(viewAlert);
            alertDialog.show();
        }

        @Override
        public void setFirstText(String tag) {
            for (int i = 0; i < tags.length; i++) {
                if (tags[i].equals(tag)) {
                    firstText.setText(tagsString[i]);
                    firstText.setTag(tag);
                }
            }
        }

        @Override
        public void setSecondText(String text) {
            secondText.setText(text);
            originalSecondTextString = text;
        }
    }
}
