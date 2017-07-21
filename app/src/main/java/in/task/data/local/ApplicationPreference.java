package in.task.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import in.task.data.DataManagerLogic;
import in.task.injection.ApplicationContext;
import in.task.injection.PreferenceName;

/**
 * Created by vivek on 21/07/17.
 */

public class ApplicationPreference implements ApplicationPrefrenceLogic {

    private static final String PREF_KEY_USER_LOGGED_IN_STATE = "PREF_KEY_USER_LOGGED_IN_STATE";
    private static final String PREF_KEY_CURRENT_USER_PHONE = "PREF_KEY_CURRENT_USER_PHONE";
    private static final String PREF_KEY_CURRENT_USER_PASSWORD = "PREF_KEY_CURRENT_USER_PASS";
    private static final String PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";
    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";


    private final SharedPreferences sharedPreferences;

    @Inject
    public ApplicationPreference(@ApplicationContext Context context,
                                 @PreferenceName String name) {
        this.sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    @Override
    public void saveUserPhone(@NonNull String userPhone) {
        sharedPreferences.edit().putString(ApplicationPreference.PREF_KEY_CURRENT_USER_PHONE, userPhone).apply();
    }

    @Override
    public String getSavedUserPhone() {
        return sharedPreferences.getString(ApplicationPreference.PREF_KEY_CURRENT_USER_PHONE, "");
    }

    @Override
    public void saveUserPassword(@NonNull String userPassword) {
        sharedPreferences.edit().putString(ApplicationPreference.PREF_KEY_CURRENT_USER_PASSWORD, userPassword).apply();
    }

    @Override
    public String getSavedUserPassword() {
        return sharedPreferences.getString(PREF_KEY_CURRENT_USER_PASSWORD, "");
    }

    @Override
    public void saveUserEmail(@NonNull String userEmail) {
        sharedPreferences.edit().putString(ApplicationPreference.PREF_KEY_CURRENT_USER_EMAIL, userEmail).apply();
    }

    @Override
    public String getSavedUserEmail() {
        return sharedPreferences.getString(PREF_KEY_CURRENT_USER_EMAIL, "");
    }

    @Override
    public int getUserLoggedInState() {
        return sharedPreferences.getInt(PREF_KEY_USER_LOGGED_IN_STATE, 2);
    }

    @Override
    public void saveUserLoggedInState(int loggedInState) {
        sharedPreferences.edit().putInt(ApplicationPreference.PREF_KEY_USER_LOGGED_IN_STATE, loggedInState).apply();
    }

    @Override
    public void saveUserName(@NonNull String userName) {
        sharedPreferences.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply();
    }

    @Override
    public String getUserName() {
        return sharedPreferences.getString(PREF_KEY_CURRENT_USER_NAME, "");
    }
}
