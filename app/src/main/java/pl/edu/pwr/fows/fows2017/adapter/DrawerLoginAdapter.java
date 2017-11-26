package pl.edu.pwr.fows.fows2017.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import pl.edu.pwr.fows.fows2017.BuildConfig;
import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.customViews.CustomSpinnerLoginAdapter;
import pl.edu.pwr.fows.fows2017.presenter.LoginPresenter;
import pl.edu.pwr.fows.fows2017.view.adapter.DrawerLoginAdapterView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public class DrawerLoginAdapter implements AdapterView.OnItemSelectedListener, DrawerLoginAdapterView {

    private final Spinner spinner;
    private final Context context;
    private LoginPresenter presenter;
    private ArrayList<String> categoriesTag;
    private CustomSpinnerLoginAdapter adapter;
    private Boolean isUser;

    public DrawerLoginAdapter(Spinner spinner, Context context) {
        this.spinner = spinner;
        this.context = context;
        categoriesTag = new ArrayList<>();
        isUser = false;
        spinner.setOnItemSelectedListener(this);
    }

    public void setPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
        this.presenter.setLoginButtonView(this);
    }

    @Override
    public void setNotLoginCategories() {
        isUser = false;
        categoriesTag.clear();
        categoriesTag.add(context.getResources().getString(R.string.anonymous));
        categoriesTag.add("LOGIN");
        categoriesTag.add("CREATE_ACCOUNT");
        setSpinner(categoriesTag);
    }

    @Override
    public void setLoginCategories(String user) {
        this.isUser = true;
        categoriesTag.clear();
        categoriesTag.add(user);
        categoriesTag.add("ACCOUNT");
        categoriesTag.add("SIGN_OUT");
        setSpinner(categoriesTag);
    }

    @Override
    public void setUserVerify(Boolean verify) {
        if (adapter != null && isUser)
            adapter.isVerify(verify);
    }

    private void setSpinner(ArrayList<String> categoriesTag) {
        adapter = new CustomSpinnerLoginAdapter(context, R.layout.row_navigation_spinner_text, categoriesTag);
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
            if (BuildConfig.DEBUG)
                Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
