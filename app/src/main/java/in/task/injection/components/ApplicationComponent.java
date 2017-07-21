package in.task.injection.components;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import in.task.ZoloApplication;
import in.task.data.DataManagerLogic;
import in.task.injection.ApplicationContext;
import in.task.injection.module.ApplicationModule;

/**
 * Created by vivek on 21/07/17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    void inject(ZoloApplication zoloApplication);

    DataManagerLogic datamangerLogic();

    Application application();

}
