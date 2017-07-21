package in.task.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by vivek on 21/07/17.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationContext {
}
