package in.task.ui.forgotpassword;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import in.task.Utils;
import in.task.base.BaseViewModel;
import in.task.data.DataManagerLogic;
import in.task.rx.RXBus;
import in.task.rx.event.Events;

/**
 * Created by vivek on 21/07/17.
 */

public class ForgotPasswordScreenViewModel extends BaseViewModel<ForgotPasswordScreenCallback> {

    @Inject
    public ForgotPasswordScreenViewModel(DataManagerLogic dataManagerLogic) {
        super(dataManagerLogic);
    }

    public void onLoginButtonClicked() {
        getCallback().openLoginFragment();
    }

    public void onResetPasswordClicked() {
        getCallback().initiateResetPassword();
    }

    public void checkIfUserRegistered(@NonNull String email) {
        if (getDataManagerLogic().checkIfEmailIsAlreadyRegistered(email)) {
            getCallback().sendEmail();
        } else {
            getCallback().showUserNotRegisteredError();
        }
    }

    public boolean onFieldChanged(String email) {
        boolean result = true;
        if (!Utils.isEmailValid(email)) {
            RXBus.getInstance().sendEvent(new Events.EmailEvent(false));
            result = false;
        } else {
            RXBus.getInstance().sendEvent(new Events.EmailEvent(true));
        }
        return result;
    }
}
