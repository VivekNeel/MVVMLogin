package in.task.ui.login;

import android.os.Bundle;

import in.task.base.BaseFragment;

/**
 * Created by vivek on 21/07/17.
 */

public class LoginFragment extends BaseFragment implements LoginScreenCallback {

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void navigateToProfile() {

    }

    @Override
    public void login() {

    }

    @Override
    public void navigateToRegisterFragment() {

    }

    @Override
    public void navigateToForgotPasswordFragment() {

    }

    @Override
    public void showUserNotRegisteredError() {

    }
}
