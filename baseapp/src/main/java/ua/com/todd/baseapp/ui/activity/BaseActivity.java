package ua.com.todd.baseapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import ua.com.todd.baseapp.R;
import ua.com.todd.baseapp.ui.fragment.FragmentLauncher;

public abstract class BaseActivity extends ActionBarActivity {

    private FragmentLauncher fragmentLauncher = new FragmentLauncher(this);
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Class<?> cls = getClass();
        LayoutId annotations = cls.getAnnotation(LayoutId.class);
        if(annotations == null)
            throw new RuntimeException("Activity must contain LayoutId annotation");
        setBaseContentView(annotations.id());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    void setBaseContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base);
        ViewStub viewStub = (ViewStub) findViewById(R.id.base_content);
        viewStub.setLayoutResource(layoutResID);
        viewStub.inflate();
    }

    @Override
    public void onBackPressed(){
        if (!getFragmentLauncher().back()) {
            super.onBackPressed();
        }
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

    @Override
    final public void setContentView(int layoutResID) {
        throw new RuntimeException("Activity must use LayoutId annotations");
    }

    @Override
    final public void setContentView(View view) {
        throw new RuntimeException("Activity must use LayoutId annotations");
    }

    @Override
    final public void setContentView(View view, ViewGroup.LayoutParams params) {
        throw new RuntimeException("Activity must use LayoutId annotations");
    }
}
