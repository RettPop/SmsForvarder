package ua.com.todd.baseapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewStub;

import ua.com.todd.baseapp.R;
import ua.com.todd.baseapp.ui.fragment.FragmentLauncher;

public abstract class BaseActivity extends ActionBarActivity {

    private FragmentLauncher fragmentLauncher = new FragmentLauncher(this);
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void setContentView(int layoutResID) {
        ViewStub viewStub = (ViewStub) findViewById(R.id.base_content);
        viewStub.setLayoutResource(layoutResID);
        viewStub.inflate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public FragmentLauncher getFragmentLauncher() {
        return fragmentLauncher;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}
