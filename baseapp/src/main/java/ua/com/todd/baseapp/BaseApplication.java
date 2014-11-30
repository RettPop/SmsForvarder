package ua.com.todd.baseapp;

import android.app.Application;

import ua.com.todd.baseapp.ui.fragment.FragmentFactory;
import ua.com.todd.baseapp.managers.BaseNetworkManager;
import ua.com.todd.baseapp.managers.BasePreferenceManager;

public class BaseApplication extends Application {

    private static BaseApplication application;
    private BaseNetworkManager networkManager;
    private BasePreferenceManager preferenceManager;
    private FragmentFactory fragmentFactory;

    public static BaseApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        networkManager = createNetworkManager();
        preferenceManager = createPreferenceManager();
        fragmentFactory = createFragmentFactory();
    }

    protected FragmentFactory createFragmentFactory() {
        return null;
    }

    protected BaseNetworkManager createNetworkManager(){
        return new BaseNetworkManager(this);
    }

    public  <T extends BaseNetworkManager> T getNetworkManager() {
        return (T) networkManager;
    }

    protected BasePreferenceManager createPreferenceManager(){
        return new BasePreferenceManager(this);
    }

    public  <T extends BasePreferenceManager> T getPreferenceManager() {
        return (T) preferenceManager;
    }

    public FragmentFactory getFragmentFactory() {
        return fragmentFactory;
    }
}
