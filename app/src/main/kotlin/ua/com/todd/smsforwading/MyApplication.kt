package ua.com.todd.smsforwading

import ua.com.todd.baseapp.BaseApplication
import ua.com.todd.smsforwading.data.HelperFactory
import ua.com.todd.smsforwading.managers.PreferenceManager
import ua.com.todd.baseapp.utils.Log
import ua.com.todd.baseapp.ui.fragment.FragmentFactory
import ua.com.todd.baseapp.ui.menu.IMenuFactory
import kotlin.properties.Delegates
import com.dynamixsoftware.ErrorAgent

public class MyApplication : BaseApplication() {

    class object {
        private var app: MyApplication by Delegates.notNull()
        public fun app(): MyApplication = app
    }

    override fun onCreate() {
        super.onCreate()
        if (!BuildConfig.DEBUG)
            ErrorAgent.register(this, 126);
        app = this;
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
