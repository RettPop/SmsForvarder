package ua.com.todd.baseapp.managers;

import android.app.Application;

public class BaseManager<T extends Application> {
    private T app;

    public BaseManager(T app) {
        this.app = app;
    }

    protected T getApplication() {
        return app;
    }
}
