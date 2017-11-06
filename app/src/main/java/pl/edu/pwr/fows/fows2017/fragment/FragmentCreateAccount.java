package pl.edu.pwr.fows.fows2017.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.di.component.ActivityComponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentCreateAccountModule;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;
import pl.edu.pwr.fows.fows2017.presenter.LoginPresenter;
import pl.edu.pwr.fows.fows2017.view.FragmentCreateAccountView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public class FragmentCreateAccount extends BaseFragment implements FragmentCreateAccountView {

    private MaterialEditText email;
    private MaterialEditText password;
    private MaterialEditText password2;
    private MaterialEditText name;
    private MaterialEditText surname;
    private MaterialEditText university;
    private MaterialEditText company;
    private Button createAccount;
    @Inject
    LoginPresenter presenter;

    @Override
    protected Integer getLayoutId() {
        return R.layout.fragment_create_account;
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new FragmentCreateAccountModule()).inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = getView().findViewById(R.id.fragment_create_account_email);
        email.addValidator(new RegexpValidator(getContext().getResources().getString(R.string.exception_fail_email), ".+@.+\\..+"));
        password = getView().findViewById(R.id.fragment_create_account_password);
        password2 = getView().findViewById(R.id.fragment_create_account_password2);
        name = getView().findViewById(R.id.fragment_create_account_name);
        surname = getView().findViewById(R.id.fragment_create_account_surname);
        company = getView().findViewById(R.id.fragment_create_account_company);
        university = getView().findViewById(R.id.fragment_create_account_university);
        createAccount = getView().findViewById(R.id.fragment_create_account_button);
        createAccount.setOnClickListener(this::clickButtonCreateAccount);
    }

    @Override
    public void continueLoading() {

    }

    private void clickButtonCreateAccount(View view) {
        presenter.createAccount(this);
    }

    @Override
    public Boolean isAllCorrect() {
        return email.getText().toString().length()>=email.getMinCharacters()
                && password.getText().toString().length()>=password.getMinCharacters()
                && password2.getText().toString().length()>=password2.getMinCharacters()
                && name.getText().toString().length()>=name.getMinCharacters()
                && surname.getText().toString().length()>=surname.getMinCharacters();
    }

    @Override
    public Boolean isCorrectPassword() {
        return password.getText().toString().equals(password2.getText().toString());
    }

    @Override
    public Boolean isCorrectEmail() {
        return email.validate();
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
    public String getName() {
        return name.getText().toString();
    }

    @Override
    public String getSurname() {
        return surname.getText().toString();
    }

    @Override
    public String getUniversity() {
        return university.getText().toString();
    }

    @Override
    public String getCompany() {
        return university.getText().toString();
    }

}
