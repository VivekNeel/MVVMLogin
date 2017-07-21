package in.task;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import in.task.data.DataManagerLogic;
import in.task.ui.forgotpassword.ForgotPasswordScreenCallback;
import in.task.ui.forgotpassword.ForgotPasswordScreenViewModel;

import static org.mockito.Mockito.verify;

/**
 * Created by vivek on 21/07/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class ForgotPasswordViewModelTest {

    @Mock
    DataManagerLogic dataManagerLogic;

    @Mock
    ForgotPasswordScreenCallback forgotPasswordScreenCallback;

    private ForgotPasswordScreenViewModel forgotPasswordScreenViewModel;

    @Before
    public void init() throws Exception {
        forgotPasswordScreenViewModel = new ForgotPasswordScreenViewModel(dataManagerLogic);
        forgotPasswordScreenViewModel.setCallback(forgotPasswordScreenCallback);
    }

    @Test
    public void testResetPasswordSuccess() {

    }

    @Test
    public void testResetPasswordFailure() {
        String email = "vivektest@gmail.com";
        forgotPasswordScreenViewModel.checkIfUserRegistered(email);
        verify(forgotPasswordScreenCallback).showUserNotRegisteredError();

    }
}
