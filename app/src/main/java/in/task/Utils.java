package in.task;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import in.task.rx.RXBus;
import in.task.rx.event.Events;

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

    public static boolean onRegisterFieldsChanged(String phone, String email, String userName, String pass) {
        boolean result;
        result = onProfileFieldsChanged(phone, userName, email);
        if (!Utils.isPasswordValid(pass)) {
            result = false;
            RXBus.getInstance().sendEvent(new Events.PasswordEvent(false));
        } else {
            RXBus.getInstance().sendEvent(new Events.EmailEvent(true));
        }

        return result;
    }

    public static boolean onLoginFieldsChanged(String phone, String pass) {
        boolean result = true;
        if (!Utils.isPhoneNumberValid(phone)) {
            result = false;
            RXBus.getInstance().sendEvent(new Events.PhoneEvent(false));
        } else {
            RXBus.getInstance().sendEvent(new Events.PhoneEvent(true));
        }


        if (!Utils.isPasswordValid(pass)) {
            result = false;
            RXBus.getInstance().sendEvent(new Events.PasswordEvent(false));
        } else {
            RXBus.getInstance().sendEvent(new Events.EmailEvent(true));
        }

        return result;

    }

    public static boolean onProfileFieldsChanged(String phone, String username, String email) {
        boolean result = true;

        if (!Utils.isEmailValid(email)) {
            result = false;
            RXBus.getInstance().sendEvent(new Events.EmailEvent(false));
        } else {
            RXBus.getInstance().sendEvent(new Events.EmailEvent(true));
        }

        if (TextUtils.isEmpty(username)) {
            result = false;
            RXBus.getInstance().sendEvent(new Events.NameEvent(false));

        } else {
            RXBus.getInstance().sendEvent(new Events.NameEvent(true));
        }

        if (!Utils.isPhoneNumberValid(phone)) {
            result = false;
            RXBus.getInstance().sendEvent(new Events.PhoneEvent(false));
        } else {
            RXBus.getInstance().sendEvent(new Events.PhoneEvent(true));
        }

        return result;
    }
}
