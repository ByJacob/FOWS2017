package pl.edu.pwr.fows.fows2017.presenter;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.User;
import pl.edu.pwr.fows.fows2017.presenter.base.BasePresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;
import pl.edu.pwr.fows.fows2017.view.FragmentCreateAccountView;
import pl.edu.pwr.fows.fows2017.view.FragmentLoginView;
import pl.edu.pwr.fows.fows2017.view.adapter.DrawerLoginAdapterView;
import pl.edu.pwr.fows.fows2017.view.base.BaseActivityAndFragmentView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public class LoginPresenter extends BasePresenter<FragmentCreateAccountView> {

    private DrawerLoginAdapterView loginButtonView;
    private User user;

    public LoginPresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        super(factory, baseActivityView);
    }

    @Override
    public void onViewTaken(FragmentCreateAccountView view) {
        this.view = view;
        updateUser();
        this.baseActivityView.disableLoadingBar();
        this.view.continueLoading();
    }

    public void showFragment(String tag) {
        baseActivityView.blockContainerClick(false);
        baseActivityView.changeMainFragment(tag);
    }

    public void createAccount(FragmentCreateAccountView fragmentCreateAccount) {
        if (!fragmentCreateAccount.isAllCorrect()) {
            baseActivityView.showMessage("INCOMPLETE", false);
        } else if (!fragmentCreateAccount.isCorrectPassword()) {
            baseActivityView.showMessage("PASS_NOT_EQUAL", false);
        } else if (!fragmentCreateAccount.isCorrectEmail()) {
            baseActivityView.showMessage("FAIL_EMAIL", false);
        } else {
            String email = fragmentCreateAccount.getEmail();
            String password = fragmentCreateAccount.getPassword();
            String name = fragmentCreateAccount.getName();
            String surname = fragmentCreateAccount.getSurname();
            String displayName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase() +
                    " " + surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase();
            factory.addUserAndLogin(email, password, displayName).execute().subscribe(this::onAddSuccessUser);
        }
    }

    private void onAddSuccessUser(Boolean aBoolean) {
        if (aBoolean) {
            baseActivityView.showMessage("ADD_ACCOUNT", null);
            updateUser();
            baseActivityView.showPreviousFragment();
        } else {
            loginButtonView.setNotLoginCategories();
        }
    }

    private void updateUser() {
        factory.getUser().execute().subscribe(this::fetchUserSuccess, this::fetchUserFail);
    }

    private void fetchUserFail(Throwable throwable) {

    }

    private void fetchUserSuccess(User user) {
        this.user = user;
        if (user.getDisplayName().length() > 1)
            loginButtonView.setLoginCategories(user.getDisplayName());
        else
            fetchUserFail(null); //TODO add message when create fail
    }

    public void setLoginButtonView(DrawerLoginAdapterView loginButtonView) {
        this.loginButtonView = loginButtonView;
    }

    public void userLogin(FragmentLoginView fragmentLogin) {
        factory.loginUser(fragmentLogin.getEmail(), fragmentLogin.getPassword()).execute().subscribe(this::onLoginSuccess);
    }

    private void onLoginSuccess(Boolean aBoolean) {
        if(aBoolean){
            baseActivityView.showMessage("LOGIN", null);
            updateUser();
            baseActivityView.showPreviousFragment();
        } else {
            loginButtonView.setNotLoginCategories(); //TODO add message when login fail
        }
    }
}
