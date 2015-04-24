package ua.com.todd.baseapp.ui.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import de.greenrobot.event.EventBus;
import ua.com.todd.baseapp.BaseApplication;
import ua.com.todd.baseapp.R;
import ua.com.todd.baseapp.model.OnBackPressedEvent;
import ua.com.todd.baseapp.ui.fragment.FragmentFactory;
import ua.com.todd.baseapp.utils.Log;

public abstract class BaseActivity extends ActionBarActivity {

    final protected Log LOG = new Log(getClass());

    private Toolbar toolbar;
    private BaseApplication baseApplication = BaseApplication.app();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        Class<?> cls = getClass();
        LayoutId annotations = cls.getAnnotation(LayoutId.class);
        if (annotations == null)
            throw new RuntimeException("Activity must contain LayoutId annotation");
        setBaseContentView(annotations.id());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void addToolbarView(int layoutId) {
        View.inflate(this, layoutId, toolbar);
    }

    void setBaseContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base);
        ViewStub viewStub = (ViewStub) findViewById(R.id.base_content);
        viewStub.setLayoutResource(layoutResID);
        viewStub.inflate();
    }

    @Override
    public void onBackPressed() {
        EventBus.getDefault().post(new OnBackPressedEvent());
    }

    final public void onBack(){
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public BaseApplication getBaseApplication() {
        return baseApplication;
    }

    public Fragment getFragment(FragmentFactory.IBaseFragmentType type) {
        return baseApplication.getFragmentFactory().getFragment(type);
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
