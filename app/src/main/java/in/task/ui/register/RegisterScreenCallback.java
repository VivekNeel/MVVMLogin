package in.task.ui.register;

/**
 * Created by vivek on 21/07/17.
 */

public interface RegisterScreenCallback {

    void navigateToProfileActivity();

    void showUserAleradyRegisteredError();

    void showGenericInputFieldErorr();

    void register();

    void navigateToLoginFragment();
}
