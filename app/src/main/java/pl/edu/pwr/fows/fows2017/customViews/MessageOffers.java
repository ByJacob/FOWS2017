package pl.edu.pwr.fows.fows2017.customViews;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.09.2017.
 */

public class MessageOffers {

    static public void showMessageOffer(Context context, String normal, String media,
                                        BaseActivityView view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder//.setMessage(R.string.offers)
                .setPositiveButton(R.string.offer1, clickOffer(normal, context, view))
                .setNegativeButton(R.string.offer2, clickOffer(media, context, view));
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    static private DialogInterface.OnClickListener clickOffer(String url, Context context,
                                                              BaseActivityView view){
        return (dialogInterface, i) -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException exception) {
                view.showMessage("BROWSER", true);
            }
        };
    }
}
