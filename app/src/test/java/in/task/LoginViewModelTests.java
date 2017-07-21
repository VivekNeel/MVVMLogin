package in.task;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import in.task.data.DataManagerLogic;
import in.task.ui.login.LoginScreenCallback;
import in.task.ui.login.LoginScreenViewModel;

import static org.mockito.Mockito.verify;

/**
 * Created by vivek on 21/07/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginViewModelTests {

    @Mock
    DataManagerLogic dataManagerLogic;

    @Mock
    LoginScreenCallback loginScreenCallback;

    private LoginScreenViewModel loginScreenViewModel;

    @Before
    public void init() throws Exception {
        loginScreenViewModel = new LoginScreenViewModel(dataManagerLogic);
        loginScreenViewModel.setCallback(loginScreenCallback);
    }


    @Test
    public void testLoginSuccess() {
        String phoneNumber = "09909403943S";
        String password = "password";

        loginScreenViewModel.doLogin(phoneNumber, password);

        verify(dataManagerLogic).saveUserPassword(password);
        verify(loginScreenCallback).navigateToProfile();

    }

    @Test
    public void testLoginFailure() {
        String phoneNumber = "09909403943S";
        String password = "password";

        loginScreenViewModel.doLogin(phoneNumber, password);
        verify(loginScreenCallback).showUserNotRegisteredError();
    }
}
