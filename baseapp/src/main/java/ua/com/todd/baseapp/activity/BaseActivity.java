package ua.com.todd.baseapp.activity;

import android.app.Activity;
import android.os.Bundle;

import ua.com.todd.baseapp.R;
import ua.com.todd.baseapp.fragment.FragmentLauncher;

public class BaseActivity extends Activity {

    private FragmentLauncher fragmentLauncher = new FragmentLauncher(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayoutId());
    }

    protected int getActivityLayoutId(){
        return R.layout.activity_base;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public FragmentLauncher getFragmentLauncher() {
        return fragmentLauncher;
    }
}
