package pl.edu.pwr.fows.fows2017.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.map.DrawerMenuItemMap;
import pl.edu.pwr.fows.fows2017.presenter.DrawerMenuPresenter;
import pl.edu.pwr.fows.fows2017.view.DrawerMenuRowView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 23.07.2017.
 */

public class DrawerMenuAdapter extends RecyclerView.Adapter<DrawerMenuAdapter.NavViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private DrawerMenuPresenter presenter;

    public DrawerMenuAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setPresenter(DrawerMenuPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public NavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        return new NavViewHolder(view);

    }

    @Override
    public void onBindViewHolder(NavViewHolder holder, int position) {
        presenter.configureMenuRow(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getMenusCount();
    }

    public class NavViewHolder extends RecyclerView.ViewHolder implements DrawerMenuRowView {
        private TextView title;
        private ImageView statusIcon;
        public NavViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.menu_item_title);
            statusIcon = itemView.findViewById(R.id.menu_item_status_icon);
        }

        @Override
        public void displayTitle(String tag) {
            title.setText(context.getString(DrawerMenuItemMap.getTitle(tag)));
        }

        @Override
        public void setIdFragment(String tag) {

        }

        @Override
        public void setIconToActive(Boolean isActive) {
            if(isActive){
                statusIcon.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), PorterDuff.Mode.DST_ATOP);
                statusIcon.setAlpha(1L);
            }else{
                statusIcon.setColorFilter(null);
                statusIcon.setAlpha((float) 0.54);
            }
        }
    }
}
