package in.task;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import in.task.data.DataManagerLogic;
import in.task.ui.register.RegisterScreenCallback;
import in.task.ui.register.RegisterScreenViewModel;

import static org.mockito.Mockito.verify;

/**
 * Created by vivek on 21/07/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class RegisterScreenViewModelTest {

    @Mock
    RegisterScreenCallback registerScreenCallback;

    @Mock
    DataManagerLogic dataManagerLogic;

    private RegisterScreenViewModel registerScreenViewModel;

    @Before
    public void init() throws Exception {
        registerScreenViewModel = new RegisterScreenViewModel(dataManagerLogic);
        registerScreenViewModel.setCallback(registerScreenCallback);
    }

    @Test
    public void testRegistrationSuccess() {
        String phoneNumber = "09909403943S";
        String password = "password";
        String email = "vivektest@gmail.com";
        String userName = "vivekneel";

        registerScreenViewModel.doRegister(phoneNumber, password, email , userName);
        verify(dataManagerLogic).saveUserPassword(password);
        verify(dataManagerLogic).saveUserEmail(email);
        verify(dataManagerLogic).saveUserPhone(phoneNumber);
        verify(dataManagerLogic).saveUserLoggedInState(DataManagerLogic.USER_LOGGED_IN);
        verify(registerScreenCallback).navigateToProfileActivity();


    }

    @Test
    public void testRegistrationFailure() {
        String phoneNumber = "09909403943S";
        String password = "password";
        String email = "vivektest@gmail.com";
        String userName = "vivekneel";

        registerScreenViewModel.doRegister(phoneNumber, password, email , userName);
        verify(registerScreenCallback).showUserAleradyRegisteredError();

    }
}
