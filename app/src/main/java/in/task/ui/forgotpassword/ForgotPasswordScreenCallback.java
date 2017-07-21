package in.task.ui.forgotpassword;

/**
 * Created by vivek on 21/07/17.
 */

public interface ForgotPasswordScreenCallback {

    void showUserNotRegisteredError();

    void sendEmail();

    void onEmailSuccess();

    void initiateResetPassword();

    void openLoginFragment();
}
