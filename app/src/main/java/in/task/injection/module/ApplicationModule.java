package in.task.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.task.AppConstants;
import in.task.data.ApplicationDataManager;
import in.task.data.DataManagerLogic;
import in.task.data.local.ApplicationPrefrenceLogic;
import in.task.injection.ApplicationContext;
import in.task.injection.PreferenceName;

/**
 * Created by vivek on 21/07/17.
 */

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    public Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    public Context provideApplicationContext() {
        return application;
    }

    @Provides
    @PreferenceName
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManagerLogic provideDataManager(ApplicationDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    ApplicationPrefrenceLogic providePreferencesHelper(ApplicationPrefrenceLogic appPreferencesHelper) {
        return appPreferencesHelper;
    }

}
