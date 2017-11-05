package pl.edu.pwr.fows.fows2017.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.customViews.CustomSpinnerLoginAdapter;
import pl.edu.pwr.fows.fows2017.presenter.LoginPresenter;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public class DrawerLoginAdapter implements AdapterView.OnItemSelectedListener {

    private final Spinner spinner;
    private final Context context;
    private LoginPresenter presenter;
    private ArrayList<String> categoriesTag;

    public DrawerLoginAdapter(Spinner spinner, Context context) {
        this.spinner = spinner;
        this.context = context;
        categoriesTag = new ArrayList<>();
        spinner.setOnItemSelectedListener(this);
    }

    public void setPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    public void setNotLogginCategories() {
        categoriesTag.clear();
        categoriesTag.add("LOGIN");
        categoriesTag.add("CREATE_ACCOUNT");
        setSpinner(categoriesTag);
    }

    private void setSpinner(ArrayList<String> categoriesTag) {
        CustomSpinnerLoginAdapter adapter = new CustomSpinnerLoginAdapter(context, R.layout.row_navigation_spinner_text, categoriesTag);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        // On selecting a spinner item
        if (position > 0) {
            String item = adapterView.getItemAtPosition(position).toString();
            presenter.showFragment(categoriesTag.get(position));
            spinner.setSelection(0);
            // Showing selected spinner item
            Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
