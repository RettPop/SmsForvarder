package ua.com.todd.baseapp;

import android.app.Application;

import ua.com.todd.baseapp.managers.BaseNetworkManager;
import ua.com.todd.baseapp.managers.BasePreferenceManager;

public class BaseApplication extends Application {

    private static BaseApplication application;
    private BaseNetworkManager networkManager;
    private BasePreferenceManager preferenceManager;

    public static BaseApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        networkManager = createNetworkManager();
        preferenceManager = createPreferenceManager();
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
}
