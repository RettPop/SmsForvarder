package ua.com.todd.smsforwading

import ua.com.todd.baseapp.BaseApplication
import ua.com.todd.smsforwading.data.HelperFactory
import ua.com.todd.smsforwading.managers.PreferenceManager

public class MyApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        HelperFactory.setHelper(getApplicationContext())
    }

    override fun onTerminate() {
        HelperFactory.releaseHelper()
        super.onTerminate()
    }

    override fun createPreferenceManager(): PreferenceManager {
        return PreferenceManager(this)
    }
}
