package in.task.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import in.task.R;
import in.task.base.BaseActivity;
import in.task.ui.forgotpassword.ForgotPasswordFragment;
import in.task.ui.login.LoginFragment;
import in.task.ui.register.RegisterFragment;

/**
 * Created by vivek on 21/07/17.
 */

public class LoginActivity extends BaseActivity {

    public static final String TAG_REGISTER = "register";
    public static final String TAG_LOGIN = "login";
    public static final String TAG_FORGOT_PASSWORD = "forgot";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        if (savedInstanceState == null) {
            switchFragment(TAG_LOGIN);
        }
    }

    public void switchFragment(String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        switch (tag) {
            case TAG_LOGIN:
                fragmentTransaction.replace(R.id.fragment_container, LoginFragment.newInstance(), tag);

                break;

            case TAG_REGISTER:
                fragmentTransaction.replace(R.id.fragment_container, RegisterFragment.newInstance(), tag);
                fragmentTransaction.addToBackStack(null);
                break;
            case TAG_FORGOT_PASSWORD:
                fragmentTransaction.replace(R.id.fragment_container, ForgotPasswordFragment.newInstance(), tag);
                fragmentTransaction.addToBackStack(null);
                break;
        }

        fragmentTransaction.commit();
    }

}
