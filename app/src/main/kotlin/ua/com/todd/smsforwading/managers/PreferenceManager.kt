package ua.com.todd.smsforwading.managers

import ua.com.todd.baseapp.managers.BasePreferenceManager
import ua.com.todd.smsforwading.MyApplication

public class PreferenceManager(app: MyApplication) : BasePreferenceManager<MyApplication>(app) {

    enum class Keys : BasePreferenceManager.IKey {
        EMAIL
        PASS
        PORT
        HOST
    }

    public fun storeEmail(str: String) {
        storeValue(Keys.EMAIL, str)
    }

    public fun restoreEmail(): String {
        return restoreString(Keys.EMAIL)
    }

    public fun storePassword(str: String) {
        storeValue(Keys.PASS, str)
    }

    public fun restorePassword(): String {
        return restoreString(Keys.PASS)
    }

    public fun storePort(value: Int) {
        storeValue(Keys.PORT, value)
    }

    public fun restorePort(): Int? {
        return restoreInt(Keys.PORT)
    }

    public fun storeHost(str: String) {
        storeValue(Keys.HOST, str)
    }

    public fun restoreHost(): String {
        return restoreString(Keys.HOST)
    }
}
