package pl.edu.pwr.fows.fows2017.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.presenter.FragmentContactPresenter;
import pl.edu.pwr.fows.fows2017.view.row.FragmentContactRowView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 17.08.2017.
 */

public class FragmentContactAdapter extends RecyclerView.Adapter<FragmentContactAdapter.OrganizersHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private FragmentContactPresenter presenter;

    public FragmentContactAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setPresenter(FragmentContactPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public OrganizersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_fragment_contact_item, parent, false);
        return new OrganizersHolder(view);
    }

    @Override
    public void onBindViewHolder(OrganizersHolder holder, int position) {
        presenter.configureRow(holder, position, context.getResources().getConfiguration().locale);
    }

    @Override
    public int getItemCount() {
        return presenter.getOrganizersCount();
    }

    public class OrganizersHolder extends RecyclerView.ViewHolder implements FragmentContactRowView {

        private TextView name;
        private TextView role;
        private CircleImageView avatar;
        private ImageView icon;

        public OrganizersHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.row_fragment_contact_name);
            role = itemView.findViewById(R.id.row_fragment_contact_role);
            avatar = itemView.findViewById(R.id.row_fragment_contact_picture);
            icon = itemView.findViewById(R.id.row_fragment_contact_icon);
        }

        @Override
        public void displayName(String name) {
            this.name.setText(name);
        }

        @Override
        public void displayRole(String role) {
            this.role.setText(role);
        }

        @Override
        public void displayPicture(String urlPicture) {
            Picasso.with(context).load(urlPicture).into(avatar);
        }

        @Override
        public void setActionToItem(String url) {
            icon.setOnClickListener(view -> {
                if (url.contains("@")) {
                    openEmailApp(url);
                } else if (url.contains("fb-messenger")) {
                    openFacebookApp(url);
                }
            });
        }

        @Override
        public void changeIconImage(String urlPicture) {
            switch (urlPicture.split("/")[1]){
                case "Messenger":
                    icon.setImageResource(R.drawable.ic_messenger);
            }
        }

        private void openFacebookApp(String url) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            try {
                context.startActivity(i);
            } catch (ActivityNotFoundException e) {
                try {
                    String[] splitUrl = url.split("/");
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.facebook.com/" + splitUrl[splitUrl.length-1]));
                    context.startActivity(browserIntent);
                } catch (ActivityNotFoundException ee) {
                    ee.printStackTrace();
                }
            }
        }

        private void openEmailApp(String url) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", context.getResources().getConfiguration().locale);
            String date = df.format(Calendar.getInstance().getTime());
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{url});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback Android - " + date);
            try {
                context.startActivity(Intent.createChooser(intent, "Send Email"));
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context, R.string.errorEmail, Toast.LENGTH_LONG).show();
            }
        }
    }
}
