package in.task;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import in.task.data.DataManagerLogic;
import in.task.ui.updateprofile.UpdateProfileCallback;
import in.task.ui.updateprofile.UpdateProfileScreenViewModel;

/**
 * Created by vivek on 21/07/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class UpdateProfileTest {

    @Mock
    DataManagerLogic dataManagerLogic;

    @Mock
    UpdateProfileCallback updateProfileCallback;

    private UpdateProfileScreenViewModel updateProfileScreenViewModel;

    @Before
    public void init() throws Exception {
        updateProfileScreenViewModel = new UpdateProfileScreenViewModel(dataManagerLogic);
        updateProfileScreenViewModel.setCallback(updateProfileCallback);
    }

    @Test
    public void testUpdateProfileSuccess() {
        String email = "vivektest@gmail.com";
        String userName = "vivessk";
        String phone = "39489438443";
        updateProfileScreenViewModel.updateProfile(userName, phone, email);
    }


}
