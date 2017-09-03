package pl.edu.pwr.fows.fows2017.customViews;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.presenter.DrawerMenuPresenter;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 26.08.2017.
 */

public class MessagingServiceAlertDialog {

    public static final String MESSAGING_SERVICE_TITLE = "title";
    public static final String MESSAGING_SERVICE_MESSAGE = "message";
    public static final String MESSAGING_SERVICE_FRAGMENT_TAG = "tag";

    static public void showAlertDialog(Context context, Intent intent, DrawerMenuPresenter presenter){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(intent.getExtras().getString(MESSAGING_SERVICE_TITLE))
                .setMessage(intent.getExtras().getString(MESSAGING_SERVICE_MESSAGE));
        builder.setPositiveButton(R.string.open, (dialogInterface, i) ->
                presenter.openFragment(intent.getExtras().getString(MESSAGING_SERVICE_FRAGMENT_TAG)));
        builder.setNegativeButton(R.string.open_later, null);
        AlertDialog dialog = builder.create();
        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
    }
}
