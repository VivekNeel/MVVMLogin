package in.task.data.local;

import android.support.annotation.NonNull;

import in.task.data.DataManagerLogic;

/**
 * Created by vivek on 21/07/17.
 */

public interface ApplicationPrefrenceLogic {

    void saveUserPhone(@NonNull String userPhone);

    String getSavedUserPhone();

    void saveUserPassword(@NonNull String userPassword);

    String getSavedUserPassword();

    void saveUserEmail(@NonNull String userEmail);

    String getSavedUserEmail();

    int getUserLoggedInState();

    void saveUserLoggedInState(int loggedInState);

}
