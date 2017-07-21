package in.task.ui.register;

import android.os.Bundle;

import in.task.base.BaseFragment;

/**
 * Created by vivek on 21/07/17.
 */

public class RegisterFragment extends BaseFragment implements RegisterScreenCallback {

    public static RegisterFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void navigateToProfileActivity() {
        
    }

    @Override
    public void showUserAleradyRegisteredError() {

    }

    @Override
    public void showGenericInputFieldErorr() {

    }

    @Override
    public void register() {

    }

    @Override
    public void navigateToLoginFragment() {

    }
}
