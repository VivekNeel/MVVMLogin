package in.task;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import static android.util.Patterns.EMAIL_ADDRESS;

/**
 * Created by vivek on 21/07/17.
 */

public class Utils {

    public static boolean isEmailValid(String enteredEmail) {
        return !TextUtils.isEmpty(enteredEmail) && EMAIL_ADDRESS.matcher(enteredEmail).matches();
    }

    public static boolean isPhoneNumberValid(@NonNull String enteredPhoneNumber) {
        return enteredPhoneNumber.length() >= 10;
    }

    public static boolean isPasswordValid(@NonNull String enteredPassword) {
        return enteredPassword.length() >= 5;
    }
}
