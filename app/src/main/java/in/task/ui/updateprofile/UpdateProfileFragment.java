package in.task.ui.updateprofile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import in.task.databinding.FragmentUpdateProfileBinding;
import in.task.injection.ActivityContext;
import in.task.injection.components.ActivityComponent;
import in.task.rx.RXBus;
import in.task.rx.event.Events;
import in.task.ui.LoginActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by vivek on 21/07/17.
 */

public class UpdateProfileFragment extends BaseFragment implements UpdateProfileCallback {

    @Inject
    UpdateProfileScreenViewModel updateProfileScreenViewModel;

    private FragmentUpdateProfileBinding fragmentUpdateProfileBinding;

    @Inject
    @ActivityContext
    Context context;

    public static UpdateProfileFragment newInstance() {
        Bundle args = new Bundle();
        UpdateProfileFragment fragment = new UpdateProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLogoutSuccessful() {
        LoginActivity.start(getBaseActivity());
        getBaseActivity().finish();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentUpdateProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_profile, container, false);
        ActivityComponent activityComponent = getActivityComponent();
        if (activityComponent != null) {
            activityComponent.inject(this);
        }
        fragmentUpdateProfileBinding.setUpdateProfileViewmodel(updateProfileScreenViewModel);
        updateProfileScreenViewModel.setCallback(this);
        setupListeners();
        setupViews();
        return fragmentUpdateProfileBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        compositeDisposable.add(RXBus.getInstance().toObservable().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (o instanceof Events.EmailEvent) {
                            if (((Events.EmailEvent) o).isEmailValid()) {
                                fragmentUpdateProfileBinding.etEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_green_24dp, 0);
                            } else {
                                fragmentUpdateProfileBinding.etEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                            }
                        } else if (o instanceof Events.PhoneEvent) {
                            if (((Events.PhoneEvent) o).isMobileNumberValid()) {
                                fragmentUpdateProfileBinding.etPhoneNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_green_24dp, 0);
                            } else {
                                fragmentUpdateProfileBinding.etPhoneNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                            }
                        } else if (o instanceof Events.NameEvent) {
                            if (((Events.NameEvent) o).isUserNameValid()) {
                                fragmentUpdateProfileBinding.etUserName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_green_24dp, 0);
                            } else {
                                fragmentUpdateProfileBinding.etUserName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                            }
                        }
                    }
                }));
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void initiateUpdateProfile() {
        String email = fragmentUpdateProfileBinding.etEmail.getText().toString();
        String userName = fragmentUpdateProfileBinding.etUserName.getText().toString();
        String userMobile = fragmentUpdateProfileBinding.etPhoneNumber.getText().toString();
        updateProfileScreenViewModel.updateProfile(userName, userMobile, email);
        hideKeyboard();
    }

    @Override
    public void onUpdateSuccessful() {
        createSnackBar(fragmentUpdateProfileBinding.getRoot(), getString(R.string.update_profile_successful_text));
    }

    private void setupViews() {
        updateProfileScreenViewModel.updateProfileViews();
    }

    public boolean checkFieldValidation() {
        boolean isValidationSuccessful;
        String email = fragmentUpdateProfileBinding.etEmail.getText().toString();
        String userName = fragmentUpdateProfileBinding.etUserName.getText().toString();
        String userMobile = fragmentUpdateProfileBinding.etPhoneNumber.getText().toString();
        isValidationSuccessful = updateProfileScreenViewModel.onFieldChanged(userMobile, email, userName);
        if (isValidationSuccessful) {
            fragmentUpdateProfileBinding.btUpdate.setBackgroundResource(R.color.button_enable_state);
            fragmentUpdateProfileBinding.btUpdate.setEnabled(true);

        } else {
            fragmentUpdateProfileBinding.btUpdate.setBackgroundResource(R.color.button_disable_state);
            fragmentUpdateProfileBinding.btUpdate.setEnabled(false);

        }
        return isValidationSuccessful;
    }

    public void setupListeners() {
        fragmentUpdateProfileBinding.etEmail.addTextChangedListener(new TextWatcher() {
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

        fragmentUpdateProfileBinding.etPhoneNumber.addTextChangedListener(new TextWatcher() {
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

        fragmentUpdateProfileBinding.etUserName.addTextChangedListener(new TextWatcher() {
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

    @Override
    public void showLogoutDialog() {
       final AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        updateProfileScreenViewModel.doLogout();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).create();

       alertDialog.show();
    }
}
