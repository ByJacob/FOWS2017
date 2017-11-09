package pl.edu.pwr.fows.fows2017.fragment.loginUser;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.adapter.FragmentAccountAdapter;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentAccountModule;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;
import pl.edu.pwr.fows.fows2017.presenter.LoginPresenter;
import pl.edu.pwr.fows.fows2017.view.FragmentAccountView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 06.11.2017.
 */

public class FragmentAccount extends BaseFragment implements FragmentAccountView {

    @Inject
    LoginPresenter presenter;
    private RecyclerView recyclerView;
    private FrameLayout passwordFrame;

    @Override
    protected Integer getLayoutId() {
        return R.layout.fragment_account;
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new FragmentAccountModule()).inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = getView().findViewById(R.id.fragment_account_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.onViewTakenFragmentAccount(this);
        passwordFrame = getView().findViewById(R.id.fragment_account_frame_password);
        configurePasswordFrame();
    }

    private void configurePasswordFrame() {
        ImageView actionIcon;
        TextView firstText;
        TextView secondText;
        View view = LayoutInflater.from(getContext()).inflate(R.layout.row_fragment_account_item, passwordFrame, false);
        actionIcon = view.findViewById(R.id.row_fragment_account_action_icon);
        firstText = view.findViewById(R.id.row_fragment_account_text);
        firstText.setText(getContext().getResources().getString(R.string.passwordL));
        secondText = view.findViewById(R.id.row_fragment_account_second_text);
        secondText.setText(getContext().getResources().getString(R.string.change_password));
        actionIcon.setOnClickListener(view12 -> {
            final View viewAlert = LayoutInflater.from(getContext()).inflate(R.layout.row_fragment_account_alert_dialog_password, null);
            AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                    .setPositiveButton(R.string.change, null).create();
            alertDialog.setTitle(R.string.change_info);
            alertDialog.setCancelable(false);
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getContext().getResources().getString(R.string.cancel),
                    ((dialogInterface, i) -> alertDialog.dismiss()));
            final MaterialEditText password1 = viewAlert.findViewById(R.id.fragment_account_row_alert_dialog_password1);
            final MaterialEditText password2 = viewAlert.findViewById(R.id.fragment_account_row_alert_dialog_password2);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getContext().getResources().getString(R.string.change), (dialogInterface, i) -> {});
            alertDialog.setView(viewAlert);
            alertDialog.setOnShowListener(dialogInterface -> {
                Button positive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positive.setOnClickListener(view1 -> {
                    if(password1.getText().toString().equals(password2.getText().toString())) {
                        presenter.updatePassword(password1.getText().toString());
                        dialogInterface.cancel();
                    }
                    else {
                        password2.setError(getContext().getText(R.string.exception_pass_not_equal));
                    }
                });
            });
            alertDialog.show();
        });
        passwordFrame.addView(view);

    }

    @Override
    public void continueLoading() {
        FragmentAccountAdapter adapter = new FragmentAccountAdapter(getContext());
        adapter.setPresenter(presenter);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void updateInformation() {
        for(int i=0; i<recyclerView.getAdapter().getItemCount(); i++)
            recyclerView.getAdapter().notifyItemChanged(i);
    }
}
