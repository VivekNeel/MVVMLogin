package in.task.ui.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import in.task.R;
import in.task.base.BaseFragment;
import in.task.databinding.FragmentLoginBinding;
import in.task.injection.components.ActivityComponent;
import in.task.ui.LoginActivity;
import in.task.ui.ProfileActivity;

/**
 * Created by vivek on 21/07/17.
 */

public class LoginFragment extends BaseFragment implements LoginScreenCallback {

    private static final String TAG = "LoginFragment";

    @Inject
    LoginScreenViewModel loginScreenViewModel;

    private FragmentLoginBinding fragmentLoginBinding;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        ActivityComponent activityComponent = getActivityComponent();
        if (activityComponent != null) {
            activityComponent.inject(this);
        }

        loginScreenViewModel.setCallback(this);
        fragmentLoginBinding.setLoginViewmodel(loginScreenViewModel);
        setupListeners();

        return fragmentLoginBinding.getRoot();
    }

    @Override
    public void navigateToProfile() {
        ProfileActivity.start(getBaseActivity());
        getBaseActivity().finish();
    }

    @Override
    public void login() {
        String userMobile = fragmentLoginBinding.etPhoneNumber.getText().toString();
        String userPassword = fragmentLoginBinding.etPassword.getText().toString();
        loginScreenViewModel.doLogin(userMobile, userPassword);
    }

    @Override
    public void navigateToRegisterFragment() {
        ((LoginActivity) getBaseActivity()).switchFragment(LoginActivity.TAG_REGISTER);
    }

    @Override
    public void navigateToForgotPasswordFragment() {
        ((LoginActivity) getBaseActivity()).switchFragment(LoginActivity.TAG_FORGOT_PASSWORD);

    }

    @Override
    public void showUserNotRegisteredError() {
        ViewGroup viewGroup = (ViewGroup) fragmentLoginBinding.getRoot();
        createSnackBar(viewGroup, getString(R.string.error_invalid_credential));
    }

    public boolean checkFieldValidation() {
        boolean isValidationSuccessful;
        String userMobile = fragmentLoginBinding.etPhoneNumber.getText().toString();
        String userPassword = fragmentLoginBinding.etPassword.getText().toString();
        isValidationSuccessful = loginScreenViewModel.onFieldChanged(userMobile, userPassword);
        if (isValidationSuccessful) {
            fragmentLoginBinding.btLogin.setBackgroundResource(R.color.button_enable_state);
            fragmentLoginBinding.btLogin.setEnabled(true);

        } else {
            fragmentLoginBinding.btLogin.setBackgroundResource(R.color.button_disable_state);
            fragmentLoginBinding.btLogin.setEnabled(false);

        }
        return isValidationSuccessful;
    }

    public void setupListeners() {


        fragmentLoginBinding.etPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkFieldValidation();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        fragmentLoginBinding.etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkFieldValidation();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
