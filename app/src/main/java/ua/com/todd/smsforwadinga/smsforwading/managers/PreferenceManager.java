package ua.com.todd.smsforwadinga.smsforwading.managers;

import ua.com.todd.baseapp.managers.BasePreferenceManager;
import ua.com.todd.smsforwadinga.smsforwading.MyApplication;

public class PreferenceManager extends BasePreferenceManager<MyApplication> {

    enum Keys implements IKey {
        EMAIL, PASS, PORT, HOST
    }

    public PreferenceManager(MyApplication app) {
        super(app);
    }

    public void storeEmail(String str) {
        storeValue(Keys.EMAIL, str);
    }

    public String restoreEmail() {
        return restoreString(Keys.EMAIL);
    }

    public void storePassword(String str) {
        storeValue(Keys.PASS, str);
    }

    public String restorePassword() {
        return restoreString(Keys.PASS);
    }

    public void storePort(int value) {
        storeValue(Keys.PORT, value);
    }

    public Integer restorePort() {
        return restoreInt(Keys.PORT);
    }

    public void storeHost(String str) {
        storeValue(Keys.HOST, str);
    }

    public String restoreHost() {
        return restoreString(Keys.HOST);
    }
}
