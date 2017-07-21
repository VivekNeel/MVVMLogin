package in.task.data;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.task.data.local.ApplicationPreference;

/**
 * Created by vivek on 21/07/17.
 */

@Singleton
public class ApplicationDataManager implements DataManagerLogic {


    private final ApplicationPreference applicationPreference;

    @Inject
    public ApplicationDataManager(ApplicationPreference applicationPreference) {
        this.applicationPreference = applicationPreference;
    }

    @Override
    public void saveUserPhone(@NonNull String userPhone) {
        applicationPreference.saveUserPhone(userPhone);
    }

    @Override
    public String getSavedUserPhone() {
        return applicationPreference.getSavedUserPhone();
    }

    @Override
    public void saveUserPassword(@NonNull String userPassword) {
        applicationPreference.saveUserPassword(userPassword);
    }

    @Override
    public String getSavedUserPassword() {
        return applicationPreference.getSavedUserPassword();
    }

    @Override
    public void saveUserEmail(@NonNull String userEmail) {
        applicationPreference.saveUserEmail(userEmail);
    }

    @Override
    public String getSavedUserEmail() {
        return applicationPreference.getSavedUserEmail();
    }

    @Override
    public int getUserLoggedInState() {
        return applicationPreference.getUserLoggedInState();
    }

    @Override
    public void saveUserLoggedInState(int loggedInState) {
        applicationPreference.saveUserLoggedInState(loggedInState);
    }

    @Override
    public boolean checkIfEmailIsAlreadyRegistered(@NonNull String email) {
        return getSavedUserEmail().equals(email);
    }

    @Override
    public boolean checkIfNumberIsAlreadyRegistered(@NonNull String phone) {
        return getSavedUserPhone().equals(phone);
    }

    @Override
    public boolean checkIfUserIsRegistered(@NonNull String phone, @NonNull String password) {
        return getSavedUserPhone().equals(phone) && getSavedUserPassword().equals(password);
    }
}
