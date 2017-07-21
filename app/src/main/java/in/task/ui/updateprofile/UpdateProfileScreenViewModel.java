package in.task.ui.updateprofile;

import android.databinding.ObservableField;
import android.text.TextUtils;

import javax.inject.Inject;

import in.task.Utils;
import in.task.base.BaseViewModel;
import in.task.data.DataManagerLogic;

/**
 * Created by vivek on 21/07/17.
 */

public class UpdateProfileScreenViewModel extends BaseViewModel<UpdateProfileCallback> {

    public final ObservableField<String> userPhone = new ObservableField<>();
    public final ObservableField<String> userName = new ObservableField<>();
    public final ObservableField<String> userEmail = new ObservableField<>();


    @Inject
    public UpdateProfileScreenViewModel(DataManagerLogic dataManagerLogic) {
        super(dataManagerLogic);
    }

    public void onLogoutButtonClicked() {
        getDataManagerLogic().saveUserLoggedInState(DataManagerLogic.USER_NOT_LOGGED_IN);
        getCallback().onLogoutSuccessful();
    }

    public void onUpdateButtonClicked() {
        getCallback().initiateUpdateProfile();
    }

    public void updateProfile(String userName, String phone, String email) {
        getDataManagerLogic().updateProfile(phone, email, userName);
        getCallback().onUpdateSuccessful();
    }

    public void updateProfileViews() {
        userPhone.set(getDataManagerLogic().getSavedUserPhone());
        userName.set(getDataManagerLogic().getUserName());
        userEmail.set(getDataManagerLogic().getSavedUserEmail());
    }


    public boolean onFieldChanged(String phone, String email, String userName) {
        return Utils.onProfileFieldsChanged(phone, userName, email);
    }

}
