package in.task.injection.components;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import in.task.ZoloApplication;
import in.task.injection.module.ApplicationModule;

/**
 * Created by vivek on 21/07/17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void injectInto(ZoloApplication zoloApplication);
}
