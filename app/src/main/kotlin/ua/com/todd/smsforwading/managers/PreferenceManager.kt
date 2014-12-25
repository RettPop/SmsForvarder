package ua.com.todd.smsforwading.managers

import ua.com.todd.baseapp.managers.BasePreferenceManager
import ua.com.todd.smsforwading.MyApplication

public class PreferenceManager(app: MyApplication) : BasePreferenceManager<MyApplication>(app) {

    enum class Keys : BasePreferenceManager.IKey {
        EMAIL
        PASS
        PORT
        HOST
        IS_ENABLED
    }

    public fun storeEnabled(b : Boolean):Unit = storeValue(Keys.IS_ENABLED, b)

    public fun isEnabled(): Boolean = restoreBoolean(Keys.IS_ENABLED)

    public fun storeEmail(str: String): Unit = storeValue(Keys.EMAIL, str)

    public fun restoreEmail(): String = restoreString(Keys.EMAIL)

    public fun storePassword(str: String): Unit = storeValue(Keys.PASS, str)

    public fun restorePassword(): String = restoreString(Keys.PASS)

    public fun storePort(value: Int): Unit = storeValue(Keys.PORT, value)

    public fun restorePort(): Int = restoreInt(Keys.PORT)

    public fun storeHost(str: String): Unit = storeValue(Keys.HOST, str)

    public fun restoreHost(): String = restoreString(Keys.HOST)
}
