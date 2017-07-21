package in.task.ui.login;

/**
 * Created by vivek on 21/07/17.
 */

public interface LoginScreenCallback {

    void navigateToProfile();

    void login();

    void navigateToRegisterFragment();

    void navigateToForgotPasswordFragment();

    void showUserNotRegisteredError();
}
