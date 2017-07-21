package in.task;

import android.app.Application;

import in.task.injection.components.ApplicationComponent;
import in.task.injection.components.DaggerApplicationComponent;
import in.task.injection.module.ApplicationModule;

/**
 * Created by vivek on 21/07/17.
 */

public class ZoloApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this))
                .build();

        applicationComponent.injectInto(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
