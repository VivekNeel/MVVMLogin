package in.task.injection.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import in.task.data.DataManagerLogic;
import in.task.injection.ActivityContext;
import in.task.injection.PerActivity;
import in.task.ui.forgotpassword.ForgotPasswordScreenViewModel;
import in.task.ui.login.LoginScreenViewModel;
import in.task.ui.register.RegisterScreenViewModel;
import in.task.ui.updateprofile.UpdateProfileScreenViewModel;

/**
 * Created by vivek on 21/07/17.
 */

@Module
public class ActivityModule {
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity appCompatActivity) {
        this.mActivity = appCompatActivity;
    }

    @Provides
    @ActivityContext
    Context provideActivityContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    LoginScreenViewModel provideLoginScreenViewModel(DataManagerLogic dataManager) {
        return new LoginScreenViewModel(dataManager);
    }

    @Provides
    @PerActivity
    RegisterScreenViewModel provideRegisterViewModel(DataManagerLogic dataManager) {
        return new RegisterScreenViewModel(dataManager);
    }

    @Provides
    @PerActivity
    ForgotPasswordScreenViewModel provideForgotPasswordViewModel(DataManagerLogic dataManager) {
        return new ForgotPasswordScreenViewModel(dataManager);
    }

    @Provides
    @PerActivity
    UpdateProfileScreenViewModel provideUpdateViewMode(DataManagerLogic dataManager) {
        return new UpdateProfileScreenViewModel(dataManager);
    }
}
