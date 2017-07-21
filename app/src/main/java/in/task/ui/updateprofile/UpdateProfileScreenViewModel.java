package in.task.ui.updateprofile;

import android.text.TextUtils;

import javax.inject.Inject;

import in.task.Utils;
import in.task.base.BaseViewModel;
import in.task.data.DataManagerLogic;

/**
 * Created by vivek on 21/07/17.
 */

public class UpdateProfileScreenViewModel extends BaseViewModel<UpdateProfileCallback> {

    @Inject
    public UpdateProfileScreenViewModel(DataManagerLogic dataManagerLogic) {
        super(dataManagerLogic);
    }

    public void onUpdateButtonClicked() {
        getCallback().initiateUpdateProfile();
    }

    public void updateProfile(String userName, String phone, String email) {
        getDataManagerLogic().updateProfile(phone, email, userName);
    }


    public boolean onFieldChanged(String phone, String email, String userName, String pass) {
        boolean result = true;
        if (!Utils.isEmailValid(email)) {
            result = false;
        } else if (!Utils.isPhoneNumberValid(phone)) {
            result = false;
        } else if (TextUtils.isEmpty(userName)) {
            result = false;
        } else if (!Utils.isPasswordValid(pass)) {
            result = false;
        }
        return result;
    }

}
