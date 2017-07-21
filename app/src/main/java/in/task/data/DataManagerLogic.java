package in.task.data;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import in.task.data.local.ApplicationPrefrenceLogic;

/**
 * Created by vivek on 21/07/17.
 */

public interface DataManagerLogic extends ApplicationPrefrenceLogic {

    int USER_LOGGED_IN = 1;
    int USER_NOT_LOGGED_IN = 2;

    @Documented
    @IntDef({USER_LOGGED_IN, USER_NOT_LOGGED_IN})
    @Retention(RetentionPolicy.SOURCE)
    @interface LoggedInState {

    }

    boolean checkIfEmailIsAlreadyRegistered(@NonNull String email);

    boolean checkIfNumberIsAlreadyRegistered(@NonNull String phone);

    boolean checkIfUserIsRegistered(@NonNull String phone, @NonNull String password);

    void updateProfile(@NonNull String phone, @NonNull String email, @NonNull String userName);
}
