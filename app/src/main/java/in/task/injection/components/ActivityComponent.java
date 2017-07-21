package in.task.injection.components;

import javax.inject.Singleton;

import dagger.Component;
import in.task.injection.PerActivity;
import in.task.injection.module.ActivityModule;
import in.task.ui.forgotpassword.ForgotPasswordFragment;
import in.task.ui.login.LoginFragment;
import in.task.ui.register.RegisterFragment;

/**
 * Created by vivek on 21/07/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(RegisterFragment registerFragment);

    void inject(LoginFragment loginFragment);

    void inject(ForgotPasswordFragment forgotPasswordFragment);
}
