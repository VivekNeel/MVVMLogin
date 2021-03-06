package in.task.ui.register;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import in.task.R;
import in.task.base.BaseFragment;
import in.task.databinding.FragmentRegisterBinding;
import in.task.injection.components.ActivityComponent;
import in.task.rx.RXBus;
import in.task.rx.event.Events;
import in.task.ui.LoginActivity;
import in.task.ui.ProfileActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by vivek on 21/07/17.
 */

public class RegisterFragment extends BaseFragment implements RegisterScreenCallback {


    @Inject
    RegisterScreenViewModel registerScreenViewModel;

    private FragmentRegisterBinding fragmentRegisterBinding;

    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentRegisterBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }

        fragmentRegisterBinding.setRegisterViewmodel(registerScreenViewModel);
        registerScreenViewModel.setCallback(this);

        checkFieldValidation();
        setupListeners();

        return fragmentRegisterBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        compositeDisposable.add(RXBus.getInstance().toObservable().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (o instanceof Events.EmailEvent) {
                            if (((Events.EmailEvent) o).isEmailValid()) {
                                fragmentRegisterBinding.etEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_green_24dp, 0);
                            } else {
                                fragmentRegisterBinding.etEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                            }
                        } else if (o instanceof Events.PhoneEvent) {
                            if (((Events.PhoneEvent) o).isMobileNumberValid()) {
                                fragmentRegisterBinding.etPhoneNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_green_24dp, 0);
                            } else {
                                fragmentRegisterBinding.etPhoneNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                            }
                        } else if (o instanceof Events.NameEvent) {
                            if (((Events.NameEvent) o).isUserNameValid()) {
                                fragmentRegisterBinding.etUserName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_green_24dp, 0);
                            } else {
                                fragmentRegisterBinding.etUserName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                            }
                        } else if (o instanceof Events.PasswordEvent) {
                            if (((Events.PasswordEvent) o).isPasswordValid()) {
                                fragmentRegisterBinding.etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_green_24dp, 0);
                            } else {
                                fragmentRegisterBinding.etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                            }
                        }
                    }
                }));
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void navigateToProfileActivity() {
        ProfileActivity.start(getBaseActivity());
        getBaseActivity().finish();
    }

    @Override
    public void showUserAleradyRegisteredError() {
        createSnackBar(fragmentRegisterBinding.getRoot(), getString(R.string.error_user_already_registered));
    }

    @Override
    public void showGenericInputFieldErorr() {

    }

    @Override
    public void register() {
        String email = fragmentRegisterBinding.etEmail.getText().toString();
        String userName = fragmentRegisterBinding.etUserName.getText().toString();
        String userMobile = fragmentRegisterBinding.etPhoneNumber.getText().toString();
        String userPassword = fragmentRegisterBinding.etPassword.getText().toString();

        registerScreenViewModel.doRegister(userMobile, userPassword, email, userName);
        hideKeyboard();

    }

    @Override
    public void navigateToLoginFragment() {
        getBaseActivity().getSupportFragmentManager().popBackStack();
    }

    public void setupListeners() {
        fragmentRegisterBinding.etEmail.addTextChangedListener(new TextWatcher() {
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

        fragmentRegisterBinding.etPhoneNumber.addTextChangedListener(new TextWatcher() {
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

        fragmentRegisterBinding.etUserName.addTextChangedListener(new TextWatcher() {
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

        fragmentRegisterBinding.etPassword.addTextChangedListener(new TextWatcher() {
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

    public boolean checkFieldValidation() {
        boolean isValidationSuccessful;
        String email = fragmentRegisterBinding.etEmail.getText().toString();
        String userName = fragmentRegisterBinding.etUserName.getText().toString();
        String userMobile = fragmentRegisterBinding.etPhoneNumber.getText().toString();
        String userPassword = fragmentRegisterBinding.etPassword.getText().toString();
        isValidationSuccessful = registerScreenViewModel.onFieldChanged(userMobile, email, userName, userPassword);
        if (isValidationSuccessful) {
            fragmentRegisterBinding.btRegister.setBackgroundResource(R.color.button_enable_state);
            fragmentRegisterBinding.btRegister.setEnabled(true);

        } else {
            fragmentRegisterBinding.btRegister.setBackgroundResource(R.color.button_disable_state);
            fragmentRegisterBinding.btRegister.setEnabled(false);

        }
        return isValidationSuccessful;
    }
}
