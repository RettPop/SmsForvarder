package ua.com.todd.baseapp.utils;

public class Log {

    private Class<?> cls;
    private static boolean isEnable;

    public static void init(boolean isEnable) {
        Log.isEnable = isEnable;
    }

    public Log(Class<?> cls) {
        this.cls = cls;
    }

    public void i(String msg) {
        if (isEnable)
            android.util.Log.i(getPref(), msg);
    }

    public void d(String msg) {
        if (isEnable)
            android.util.Log.d(getPref(), msg);
    }

    public void v(String msg) {
        if (isEnable)
            android.util.Log.v(getPref(), msg);
    }

    public void e(String msg) {
        if (isEnable)
            android.util.Log.e(getPref(), msg);
    }

    private String getPref() {
        return cls.getName();
    }
}