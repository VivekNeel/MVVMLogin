package in.task.ui.login;

import javax.inject.Inject;

import in.task.Utils;
import in.task.base.BaseViewModel;
import in.task.data.DataManagerLogic;

/**
 * Created by vivek on 21/07/17.
 */

public class LoginScreenViewModel extends BaseViewModel<LoginScreenCallback> {

    @Inject
    public LoginScreenViewModel(DataManagerLogic dataManagerLogic) {
        super(dataManagerLogic);
    }

    public void onLoginButtonClicked() {
        getCallback().login();
    }

    public void onRegisterButtonClicked() {
        getCallback().navigateToRegisterFragment();
    }

    public void onForgotPasswordClicked() {
        getCallback().navigateToForgotPasswordFragment();
    }

    public void isUserLoggedIn() {
        if (getDataManagerLogic().getUserLoggedInState() == 1) {
            getCallback().navigateToProfile();
        }
    }

    public void doLogin(String phone, String pass) {
        // User has been already registered with zolo
        if (getDataManagerLogic().checkIfUserIsRegistered(phone, pass)) {
            getDataManagerLogic().saveUserPassword(pass);
            getCallback().navigateToProfile();
        } else {
            getCallback().showUserNotRegisteredError();
        }

    }

    public boolean onFieldChanged(String phone, String pass) {
      return Utils.onLoginFieldsChanged(phone , pass);
    }
}
