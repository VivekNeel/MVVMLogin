package in.task.rx.event;

/**
 * Created by vivek on 21/07/17.
 */

public class Events {

    public static class EmailEvent {
        boolean isEmailValid;

        public EmailEvent(boolean isEmailValid) {
            this.isEmailValid = isEmailValid;
        }

        public boolean isEmailValid() {
            return isEmailValid;
        }
    }

    public static class PhoneEvent {
        boolean isMobileNumberValid;

        public boolean isMobileNumberValid() {
            return isMobileNumberValid;
        }

        public PhoneEvent(boolean isMobileNumberValid) {

            this.isMobileNumberValid = isMobileNumberValid;
        }
    }

    public static class NameEvent {
        boolean isUserNameValid;

        public boolean isUserNameValid() {
            return isUserNameValid;
        }

        public NameEvent(boolean isUserNameValid) {

            this.isUserNameValid = isUserNameValid;
        }
    }

    public static class PasswordEvent {
        boolean isPasswordValid;

        public boolean isPasswordValid() {
            return isPasswordValid;
        }

        public PasswordEvent(boolean isPasswordValid) {

            this.isPasswordValid = isPasswordValid;
        }
    }
}
