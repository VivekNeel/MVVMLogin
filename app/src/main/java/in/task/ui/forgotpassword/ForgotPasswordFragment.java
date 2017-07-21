package in.task.ui.forgotpassword;

import android.os.Bundle;

import in.task.base.BaseFragment;

/**
 * Created by vivek on 21/07/17.
 */

public class ForgotPasswordFragment extends BaseFragment implements ForgotPasswordScreenCallback {

    public static ForgotPasswordFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void showUserNotRegisteredError() {
        
    }

    @Override
    public void sendEmail() {

    }

    @Override
    public void onEmailSuccess() {

    }

    @Override
    public void initiateResetPassword() {

    }

    @Override
    public void openLoginFragment() {

    }
}
