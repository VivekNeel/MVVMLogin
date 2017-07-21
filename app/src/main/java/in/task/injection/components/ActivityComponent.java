package in.task.injection.components;

import javax.inject.Singleton;

import dagger.Component;
import in.task.injection.PerActivity;
import in.task.injection.module.ActivityModule;

/**
 * Created by vivek on 21/07/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


}
