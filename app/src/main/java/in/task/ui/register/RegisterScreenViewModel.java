package in.task.ui.register;

import android.text.TextUtils;

import javax.inject.Inject;

import in.task.Utils;
import in.task.base.BaseViewModel;
import in.task.data.DataManagerLogic;

/**
 * Created by vivek on 21/07/17.
 */

public class RegisterScreenViewModel extends BaseViewModel<RegisterScreenCallback> {

    @Inject
    public RegisterScreenViewModel(DataManagerLogic dataManagerLogic) {
        super(dataManagerLogic);
    }

    public void onRegisterButtonClicked() {
        getCallback().register();
    }


    public void doRegister(String phone, String pass, String email, String userName) {
        // User has been already registered with zolo
        if (getDataManagerLogic().checkIfUserIsRegistered(email, phone)) {
            getCallback().showUserAleradyRegisteredError();
            return;
        }
        getDataManagerLogic().saveUserPhone(phone);
        getDataManagerLogic().saveUserPassword(pass);
        getDataManagerLogic().saveUserEmail(email);
        getDataManagerLogic().saveUserName(userName);
        getDataManagerLogic().saveUserLoggedInState(DataManagerLogic.USER_LOGGED_IN);
        getCallback().navigateToProfileActivity();

    }

    public void onLoginButtonClicked() {
        getCallback().navigateToLoginFragment();

    }

    public boolean onFieldChanged(String phone, String email, String userName, String pass) {
        boolean result = true;
        if (!Utils.isEmailValid(email)) {
            result = false;
        } else if (!Utils.isPhoneNumberValid(phone)) {
            result = false;
        } else if (TextUtils.isEmpty(userName)) {
            result = false;
        } else if (!Utils.isPasswordValid(pass)) {
            result = false;
        }
        return result;
    }
}
