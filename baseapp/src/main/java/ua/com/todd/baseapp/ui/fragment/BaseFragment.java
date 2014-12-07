package ua.com.todd.baseapp.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;

import ua.com.todd.baseapp.BaseApplication;
import ua.com.todd.baseapp.managers.BasePreferenceManager;
import ua.com.todd.baseapp.ui.activity.BaseActivity;
import ua.com.todd.baseapp.ui.activity.LayoutId;

public abstract class BaseFragment extends Fragment {
    private AQuery aQuery;
    private BaseApplication app;

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

    public <T extends BaseApplication> T getApp() {
        return (T) app;
    }

    public <T extends BasePreferenceManager> T getPreferenceManager() {
        return (T) app.getPreferenceManager();
    }

    public AQuery getAQuery() {
        return aQuery;
    }
}
