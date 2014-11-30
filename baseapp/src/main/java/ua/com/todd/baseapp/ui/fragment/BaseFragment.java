package ua.com.todd.baseapp.ui.fragment;

import android.app.Application;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;

import ua.com.todd.baseapp.BaseApplication;
import ua.com.todd.baseapp.managers.BasePreferenceManager;

public abstract class BaseFragment extends Fragment {
    private AQuery aQuery;
    private BaseApplication app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (BaseApplication) getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    protected abstract int getLayoutId();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aQuery = new AQuery(getActivity(), view);
    }

    public <T extends Application> T getApp() {
        return (T) app;
    }

    public <T extends BasePreferenceManager> T getPreferenceManager() {
        return (T) app.getPreferenceManager();
    }

    public AQuery getAQuery() {
        return aQuery;
    }
}
