package ua.com.todd.baseapp.managers;

import android.app.Application;

public class BaseNetworkManager<T extends Application> extends BaseManager<T> {
    public BaseNetworkManager(T app) {
        super(app);
    }
}
