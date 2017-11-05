package pl.edu.pwr.fows.fows2017.customViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pl.edu.pwr.fows.fows2017.R;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public class CustomSpinnerLoginAdapter extends ArrayAdapter {

    private final Context context;
    private TextView initials;
    private TextView text;
    private ArrayList<String> categories;


    public CustomSpinnerLoginAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public CustomSpinnerLoginAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> categories) {
        super(context, resource, categories);
        this.context = context;
        this.categories = categories;
    }

    public View getCustomView(int position, View conventView, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.row_navigation_spinner_text, viewGroup, false);
        initials = layout.findViewById(R.id.spinner_text_initials);
        StringBuilder stringInitials = new StringBuilder();
        for(int i=0; i<categories.get(0).length(); i++){
            char c = categories.get(0).charAt(i);
            if(Character.isUpperCase(c))
                stringInitials.append(c);
            if(stringInitials.length()>2)
                break;
        }
        initials.setText(stringInitials.toString());
        text = layout.findViewById(R.id.spinner_text);
        text.setText(categories.get(0));
        return layout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
}
