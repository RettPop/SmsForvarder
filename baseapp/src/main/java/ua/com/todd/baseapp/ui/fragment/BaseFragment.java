package ua.com.todd.baseapp.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;

import de.greenrobot.event.EventBus;
import ua.com.todd.baseapp.BaseApplication;
import ua.com.todd.baseapp.managers.BasePreferenceManager;
import ua.com.todd.baseapp.model.OnBackPressedEvent;
import ua.com.todd.baseapp.model.OnBackPressedResponseEvent;
import ua.com.todd.baseapp.ui.activity.BaseActivity;
import ua.com.todd.baseapp.ui.activity.LayoutId;
import ua.com.todd.baseapp.utils.Log;

public abstract class BaseFragment extends Fragment {
    private AQuery aQuery;
    private BaseApplication app;
    private BaseActivity baseActivity;
    final protected Log LOG = new Log(getClass());

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        EventBus.getDefault().register(this);
        if (activity instanceof BaseActivity)
            baseActivity = (BaseActivity) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (BaseApplication) getActivity().getApplication();
    }

    @Override
    final public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutId annotations = getClass().getAnnotation(LayoutId.class);
        if (annotations == null)
            throw new RuntimeException("Fragment must contain LayoutId annotation");
        return inflater.inflate(annotations.id(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aQuery = new AQuery(getActivity(), view);
    }

    protected void onBackPressed(){
    }

    protected void onBack(){
        EventBus.getDefault().post(new OnBackPressedResponseEvent());
    }

    public void onEvent(OnBackPressedEvent e){
        onBackPressed();
    }

    public <T extends BaseApplication> T getApp() {
        return (T) app;
    }

    public <T extends BasePreferenceManager> T getPreferenceManager() {
        return (T) app.getPreferenceManager();
    }

    public AQuery getAQuery() {
        return aQuery;
    }

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    public Fragment getFragment(FragmentFactory.IBaseFragmentType type) {
        return getBaseActivity().getFragment(type);
    }
    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }
}
