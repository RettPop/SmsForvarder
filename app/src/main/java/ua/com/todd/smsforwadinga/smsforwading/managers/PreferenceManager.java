package ua.com.todd.smsforwadinga.smsforwading.managers;

import ua.com.todd.baseapp.managers.BasePreferenceManager;
import ua.com.todd.smsforwadinga.smsforwading.MyApplication;

public class PreferenceManager extends BasePreferenceManager<MyApplication> {

    enum Keys implements IKey{
        EMAIL
    }

    public PreferenceManager(MyApplication app) {
        super(app);
    }
}
