package ua.com.todd.baseapp;

import android.app.Application;

import ua.com.todd.baseapp.managers.BaseNetworkManager;
import ua.com.todd.baseapp.managers.BasePreferenceManager;
import ua.com.todd.baseapp.ui.fragment.FragmentFactory;
import ua.com.todd.baseapp.ui.menu.IMenuFactory;

public class BaseApplication extends Application {

    private static BaseApplication application;
    private BaseNetworkManager networkManager;
    private BasePreferenceManager preferenceManager;
    private FragmentFactory fragmentFactory;
    private IMenuFactory menuFactory;

    public static BaseApplication app() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        networkManager = createNetworkManager();
        preferenceManager = createPreferenceManager();
        fragmentFactory = createFragmentFactory();
        menuFactory = createMenuFactory();
    }

    protected FragmentFactory createFragmentFactory() {
        return null;
    }

    protected IMenuFactory createMenuFactory() {
        return null;
    }

    protected BaseNetworkManager createNetworkManager() {
        return new BaseNetworkManager(this);
    }

    public <T extends BaseNetworkManager> T getNetworkManager() {
        return (T) networkManager;
    }

    protected BasePreferenceManager createPreferenceManager() {
        return new BasePreferenceManager(this);
    }

    public <T extends BasePreferenceManager> T getPreferenceManager() {
        return (T) preferenceManager;
    }

    public FragmentFactory getFragmentFactory() {
        return fragmentFactory;
    }

    public IMenuFactory getMenuFactory() {
        return menuFactory;
    }
}
