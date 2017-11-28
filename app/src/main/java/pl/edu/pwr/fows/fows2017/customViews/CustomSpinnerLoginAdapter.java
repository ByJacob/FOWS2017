package pl.edu.pwr.fows.fows2017.customViews;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.map.DrawerMenuItemMap;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public class CustomSpinnerLoginAdapter extends ArrayAdapter {

    private final Context context;
    private TextView initials;
    private TextView text;
    private ImageView imageView;
    private ArrayList<String> categoriesTag;
    private ArrayList<String> categories;
    private Boolean verify = true;


    public CustomSpinnerLoginAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public CustomSpinnerLoginAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> categoriesTag) {
        super(context, resource);
        this.categoriesTag = categoriesTag;
        this.categories = new ArrayList<>();
        for (String tag : categoriesTag) {
            if (DrawerMenuItemMap.isTag(tag))
                categories.add(context.getResources().getString(DrawerMenuItemMap.getTag(tag)));
            else
                categories.add(tag);
        }
        super.addAll(categories);
        this.context = context;
    }

    private View getCustomView(int position, View conventView, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.row_navigation_spinner_text, viewGroup, false);
        initials = layout.findViewById(R.id.spinner_text_initials);
        StringBuilder stringInitials = new StringBuilder();
        for (int i = 0; i < categories.get(0).length(); i++) {
            char c = categories.get(0).charAt(i);
            if (Character.isUpperCase(c))
                stringInitials.append(c);
            if (stringInitials.length() > 2)
                break;
        }
        if(stringInitials.length()<1) {
            String c = categories.get(0).substring(0,1).toUpperCase();
            stringInitials.append(c);
        }
        initials.setText(stringInitials.toString());
        text = layout.findViewById(R.id.spinner_text);
        text.setText(categories.get(0));
        imageView = layout.findViewById(R.id.spinner_image);
        setVerify();
        return layout;
    }

    public void isVerify(Boolean verify){
        this.verify = verify;
    }
    public void setVerify(){
        if(verify==null)
            verify = false;
        if(verify){
            imageView.setImageResource(R.drawable.ic_navigation_item);
            imageView.setAlpha(0.54f);
            initials.setVisibility(View.VISIBLE);
        } else {
            imageView.setImageResource(R.drawable.ic_error);
            imageView.setAlpha(1f);
            initials.setVisibility(View.INVISIBLE);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
}
