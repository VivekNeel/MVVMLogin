package in.task.ui.forgotpassword;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
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
import in.task.databinding.FragmentForgotPasswordBinding;
import in.task.injection.components.ActivityComponent;
import in.task.ui.LoginActivity;

/**
 * Created by vivek on 21/07/17.
 */

public class ForgotPasswordFragment extends BaseFragment implements ForgotPasswordScreenCallback {

    @Inject
    ForgotPasswordScreenViewModel forgotPasswordScreenViewModel;

    private FragmentForgotPasswordBinding fragmentForgotPasswordBinding;

    private static final int REQUEST_CODE = 109;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentForgotPasswordBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgot_password, container, false);

        ActivityComponent activityComponent = getActivityComponent();
        if (activityComponent != null) {
            activityComponent.inject(this);
        }

        fragmentForgotPasswordBinding.setForgotPasswordViewmodel(forgotPasswordScreenViewModel);
        forgotPasswordScreenViewModel.setCallback(this);
        setupListeners();
        return fragmentForgotPasswordBinding.getRoot();
    }

    public static ForgotPasswordFragment newInstance() {

        Bundle args = new Bundle();

        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showUserNotRegisteredError() {
        createSnackBar(fragmentForgotPasswordBinding.getRoot(), getString(R.string.error_invalid_credential));
    }

    @Override
    public void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{fragmentForgotPasswordBinding.etEmail.getText().toString()});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Reset password");
        // dummy new password
        intent.putExtra(Intent.EXTRA_TEXT, "Your new password is: 98fjffdiof");
        if (intent.resolveActivity(getBaseActivity().getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            onEmailSuccess();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onEmailSuccess() {
        openLoginFragment();
    }

    @Override
    public void initiateResetPassword() {
        hideKeyboard();
        String userEmail = fragmentForgotPasswordBinding.etEmail.getText().toString();
        forgotPasswordScreenViewModel.checkIfUserRegistered(userEmail);
    }

    @Override
    public void openLoginFragment() {
        ((LoginActivity) getBaseActivity()).switchFragment(LoginActivity.TAG_LOGIN);
    }

    public boolean checkFieldValidation() {
        boolean isValidationSuccessful;
        String userEmail = fragmentForgotPasswordBinding.etEmail.getText().toString();
        isValidationSuccessful = forgotPasswordScreenViewModel.onFieldChanged(userEmail);
        if (isValidationSuccessful) {
            fragmentForgotPasswordBinding.btResetPass.setBackgroundResource(R.color.button_enable_state);
            fragmentForgotPasswordBinding.btResetPass.setEnabled(true);

        } else {
            fragmentForgotPasswordBinding.btResetPass.setBackgroundResource(R.color.button_disable_state);
            fragmentForgotPasswordBinding.btResetPass.setEnabled(false);

        }
        return isValidationSuccessful;
    }

    public void setupListeners() {

        fragmentForgotPasswordBinding.etEmail.addTextChangedListener(new TextWatcher() {
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
