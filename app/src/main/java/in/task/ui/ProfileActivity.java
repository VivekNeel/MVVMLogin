package in.task.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import in.task.R;
import in.task.base.BaseActivity;
import in.task.ui.updateprofile.UpdateProfileFragment;

/**
 * Created by vivek on 21/07/17.
 */

public class ProfileActivity extends BaseActivity {

    private static final String TAG_PROFILE = "profile";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (savedInstanceState == null) {
            switchFragment(TAG_PROFILE);
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ProfileActivity.class);
        context.startActivity(starter);
    }

    private void switchFragment(String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (tag) {
            case TAG_PROFILE:
                fragmentTransaction.replace(R.id.fragment_container, UpdateProfileFragment.newInstance(), tag);
                break;
        }
        fragmentTransaction.commit();
    }
}
