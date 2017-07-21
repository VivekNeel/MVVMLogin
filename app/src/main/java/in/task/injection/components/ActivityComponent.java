package in.task.injection.components;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Singleton;

import dagger.Component;
import in.task.injection.ActivityContext;
import in.task.injection.PerActivity;
import in.task.injection.module.ActivityModule;
import in.task.ui.forgotpassword.ForgotPasswordFragment;
import in.task.ui.login.LoginFragment;
import in.task.ui.register.RegisterFragment;
import in.task.ui.updateprofile.UpdateProfileFragment;

/**
 * Created by vivek on 21/07/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ActivityContext
    Context context();

    AppCompatActivity activity();

    void inject(RegisterFragment registerFragment);

    void inject(LoginFragment loginFragment);

    void inject(ForgotPasswordFragment forgotPasswordFragment);

    void inject(UpdateProfileFragment updateProfileFragment);
}
