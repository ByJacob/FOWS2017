package pl.edu.pwr.fows.fows2017.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.customViews.CustomSpinnerLoginAdapter;
import pl.edu.pwr.fows.fows2017.presenter.DrawerMenuPresenter;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public class DrawerLoginAdapter implements AdapterView.OnItemSelectedListener {

    private final Spinner spinner;
    private final Context context;
    private DrawerMenuPresenter presenter;

    public DrawerLoginAdapter(Spinner spinner, Context context) {
        this.spinner = spinner;
        this.context = context;
        spinner.setOnItemSelectedListener(this);
    }

    public void setPresenter(DrawerMenuPresenter presenter) {
        this.presenter = presenter;
    }

    public void setNotLogginCategories() {
        ArrayList<String> categories = new ArrayList<>();
        categories.add(context.getResources().getString(R.string.login));
        categories.add(context.getResources().getString(R.string.loginWithFacebook));
        categories.add(context.getResources().getString(R.string.createAccount));
        setSpinner(categories);
    }

    private void setSpinner(ArrayList<String> categories) {
        CustomSpinnerLoginAdapter adapter = new CustomSpinnerLoginAdapter(context, R.layout.row_navigation_spinner_text, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        // On selecting a spinner item
        String item = adapterView.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
