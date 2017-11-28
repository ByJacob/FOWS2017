package pl.edu.pwr.fows.fows2017.presenter;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.User;
import pl.edu.pwr.fows.fows2017.presenter.base.BasePresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;
import pl.edu.pwr.fows.fows2017.view.FragmentAccountView;
import pl.edu.pwr.fows.fows2017.view.FragmentCreateAccountView;
import pl.edu.pwr.fows.fows2017.view.FragmentLoginView;
import pl.edu.pwr.fows.fows2017.view.adapter.DrawerLoginAdapterView;
import pl.edu.pwr.fows.fows2017.view.row.FragmentAccountRowView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public class LoginPresenter extends BasePresenter<FragmentCreateAccountView> {

    private DrawerLoginAdapterView loginButtonView;
    private FragmentAccountView fragmentAccountView;
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

    public void onViewTakenFragmentAccount(FragmentAccountView fragmentAccount) {
        if (user != null) {
            fragmentAccount.continueLoading();
            this.fragmentAccountView = fragmentAccount;
            fragmentAccountView.enableVerifyError(user.getVerify());
        } else {
            updateUser();
            baseActivityView.showPreviousFragment();
        }

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
            baseActivityView.enableLoadingBar();
            factory.addUserAndLogin(fragmentCreateAccount.getEmail(),
                    fragmentCreateAccount.getPassword(),
                    fragmentCreateAccount.getName(),
                    fragmentCreateAccount.getSurname(),
                    fragmentCreateAccount.getUniversity(),
                    fragmentCreateAccount.getCompany())
                    .execute().subscribe(this::onAddSuccessUser);
        }
    }

    private void onAddSuccessUser(Boolean aBoolean) {
        baseActivityView.disableLoadingBar();
        if (aBoolean) {
            baseActivityView.showMessage("ADD_ACCOUNT", null);
            updateUser();
            baseActivityView.showPreviousFragment();
        } else {
            loginButtonView.setNotLoginCategories();
            baseActivityView.showMessage("EMAIL_EXIST", true);
        }
    }

    private void updateUser() {
        factory.getUser().execute().subscribe(this::fetchUserSuccess, this::fetchUserFail);
    }

    private void fetchUserFail(Throwable throwable) {
        if (throwable != null)
            throwable.printStackTrace();
        loginButtonView.setNotLoginCategories();
    }

    private void fetchUserSuccess(User user) {

        if (!user.getName().isEmpty() && !user.getSurname().isEmpty()) {
            this.user = user;
            loginButtonView.setLoginCategories(user.getName() + " " + user.getSurname());
            loginButtonView.setUserVerify(user.getVerify());
        } else
            fetchUserFail(null); //TODO add message when create fail
    }

    public void setLoginButtonView(DrawerLoginAdapterView loginButtonView) {
        this.loginButtonView = loginButtonView;
    }

    public void userLogin(FragmentLoginView fragmentLogin) {
        if (fragmentLogin.getEmail().isEmpty() || fragmentLogin.getPassword().isEmpty()) {
            fragmentLogin.showErrorEmpty();
        } else {
            factory.loginUser(fragmentLogin.getEmail(), fragmentLogin.getPassword()).execute().subscribe(this::onLoginSuccess);
            baseActivityView.enableLoadingBar();
        }
    }

    private void onLoginSuccess(Boolean aBoolean) {
        baseActivityView.disableLoadingBar();
        if (aBoolean) {
            baseActivityView.showMessage("LOGIN", null);
            updateUser();
            baseActivityView.showPreviousFragment();
        } else {
            loginButtonView.setNotLoginCategories();
            baseActivityView.showMessage("LOGIN_FAIL", true);
        }
    }

    public void configureRowInAccount(FragmentAccountRowView holder, String tag) {
        holder.setFirstText(tag);
        switch (tag) {
            case "NAME":
                holder.setSecondText(user.getName());
                break;
            case "SURNAME":
                holder.setSecondText(user.getSurname());
                break;
            case "EMAIL":
                holder.setSecondText(user.getEmail());
                break;
            case "UNIVERSITY":
                holder.setSecondText(user.getUniversity());
                break;
            case "COMPANY":
                holder.setSecondText(user.getCompany());
                break;
            default:
                holder.setSecondText("");
        }
    }

    public void loginDefaultUser() {
        factory.loginCurrentUser().execute().subscribe(this::fetchDefaultUserSuccess);
    }

    private void fetchDefaultUserSuccess(Boolean aBoolean) {
        if (aBoolean)
            updateUser();
    }

    public void updatePassword(String password) {
        user.setPassword(password);
        factory.updateUserPassword(password).execute().subscribe(this::updateSuccess);
    }

    public void updateUserElement(String tag, String s) {
        baseActivityView.enableLoadingBar();
        switch (tag) {
            case "NAME":
                user.setName(s);
                break;
            case "SURNAME":
                user.setSurname(s);
                break;
            case "EMAIL":
                user.setEmail(s);
                break;
            case "UNIVERSITY":
                user.setUniversity(s);
                break;
            case "COMPANY":
                user.setCompany(s);
                break;
        }
        if (tag.equals("EMAIL"))
            factory.updateUserEmail(user.getEmail()).execute().subscribe(this::updateSuccess);
        else
            factory.updateUser(user).execute().subscribe(this::updateSuccess);
    }

    private void updateSuccess(Boolean aBoolean) {
        baseActivityView.disableLoadingBar();
        if (aBoolean) {
            updateUser();
            fragmentAccountView.updateInformation();
            baseActivityView.showMessage("UPDATE_SUCCESS", null);
        } else {
            baseActivityView.showMessage("UPDATE_FAIL", false);
        }
    }

    public void signOutUser() {
        factory.logoutCurrentUser().execute().subscribe(this::logoutUserSuccess);
    }

    private void logoutUserSuccess(Boolean aBoolean) {
        if (aBoolean) {
            updateUser();
            baseActivityView.showMessage("SIGN_OUT_SUCCESS", null);
        } else {
            baseActivityView.showMessage("SIGN_OUT_FAIL", null);
        }
    }

    public void sendEmailVerify() {
        factory.sendEmailVerifycationUser().execute().subscribe(this::sendEmailVerifySuccess);
    }

    private void sendEmailVerifySuccess(Boolean aBoolean) {
        if (aBoolean)
            baseActivityView.showMessage("SEND_VERIFY", null);
    }
}
