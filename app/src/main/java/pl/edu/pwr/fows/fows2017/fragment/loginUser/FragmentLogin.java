package pl.edu.pwr.fows.fows2017.fragment.loginUser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentLoginModule;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;
import pl.edu.pwr.fows.fows2017.presenter.LoginPresenter;
import pl.edu.pwr.fows.fows2017.view.FragmentLoginView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 06.11.2017.
 */

public class FragmentLogin extends BaseFragment implements FragmentLoginView {

    @Inject
    LoginPresenter presenter;
    private MaterialEditText email;
    private MaterialEditText password;
    private Button login;
    private Button loginFacebook;

    @Override
    protected Integer getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new FragmentLoginModule()).inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = getView().findViewById(R.id.fragment_login_editText_email);
        password = getView().findViewById(R.id.fragment_login_editText_password);
        login = getView().findViewById(R.id.fragment_login_button_login);
        login.setOnClickListener(this::onClickLogin);
        loginFacebook = getView().findViewById(R.id.fragment_login_button_facebook);
    }

    private void onClickLogin(View view) {
        email.setError("");
        password.setError("");
        presenter.userLogin(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void continueLoading() {

    }

    @Override
    public Boolean isNoEmpty() {
        return !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty();
    }

    @Override
    public String getEmail() {
        return email.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void showErrorEmpty() {

        if (getEmail().isEmpty()) {
            email.setError(getString(R.string.exception_require_field));
        }
        if (getPassword().isEmpty()) {
            password.setError(getString(R.string.exception_require_field));
        }


    }
}
