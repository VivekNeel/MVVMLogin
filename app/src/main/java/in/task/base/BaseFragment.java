package in.task.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.support.v4.app.Fragment;
import android.view.View;

import in.task.injection.components.ActivityComponent;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by vivek on 21/07/17.
 */

public class BaseFragment extends Fragment {

    private BaseActivity mActivity;

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
        }
    }

    @Override
    public void onDetach() {
        mActivity = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        super.onDetach();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }

    public void createSnackBar(View view, String message) {
        if (mActivity != null) {
            mActivity.showSnackbar(view, message);
        }
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

}
