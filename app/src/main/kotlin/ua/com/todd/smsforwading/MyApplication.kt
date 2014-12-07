package ua.com.todd.smsforwading

import ua.com.todd.baseapp.BaseApplication
import ua.com.todd.smsforwading.data.HelperFactory
import ua.com.todd.smsforwading.managers.PreferenceManager
import ua.com.todd.baseapp.utils.Log
import ua.com.todd.baseapp.ui.fragment.FragmentFactory
import ua.com.todd.baseapp.ui.menu.IMenuFactory

public class MyApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        Log.init(BuildConfig.DEBUG);
        HelperFactory.setHelper(getApplicationContext())
    }

    override fun onTerminate() {
        HelperFactory.releaseHelper()
        super.onTerminate()
    }

    override fun createPreferenceManager(): PreferenceManager {
        return PreferenceManager(this)
    }

    override fun createFragmentFactory(): FragmentFactory = ua.com.todd.smsforwading.fragment.FragmentFactory();

    override fun getMenuFactory(): IMenuFactory? {
        return MenuFactory()
    }
}
