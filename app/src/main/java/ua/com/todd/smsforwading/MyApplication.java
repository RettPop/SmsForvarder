package ua.com.todd.smsforwading;

import ua.com.todd.baseapp.BaseApplication;
import ua.com.todd.smsforwading.data.HelperFactory;
import ua.com.todd.smsforwading.managers.PreferenceManager;

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        HelperFactory.setHelper(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        HelperFactory.releaseHelper();
        super.onTerminate();
    }

    @Override
    protected PreferenceManager createPreferenceManager() {
        return new PreferenceManager(this);
    }
}
