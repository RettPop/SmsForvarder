package ua.com.todd.baseapp.utils;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Point;
import android.view.Display;

import ua.com.todd.baseapp.BaseApplication;

public class AndroidUtils {

    private static BaseApplication app = BaseApplication.getInstance();

    private static Point getDisplaySize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    public static int getScreenWidth(Activity activity) {
        return getDisplaySize(activity).x;
    }

    public static int getScreenHeight(Activity activity) {
        return getDisplaySize(activity).y;
    }

    public static int getInt(int id) {
        return app.getResources().getInteger(id);
    }

    public static int[] getIntArray(int id) {
        return app.getResources().getIntArray(id);
    }

    public static String getString(int id) {
        return app.getResources().getString(id);
    }

    public static String getString(int id, Object... obj) {
        return app.getResources().getString(id, obj);
    }

    public static String[] getArrayString(int id) {
        return app.getResources().getStringArray(id);
    }

    public static String getQuantityString(int id, int count) {
        return app.getResources().getQuantityString(id, count);
    }

    public static String getQuantityString(int id, int count, Object... obj) {
        return app.getResources().getQuantityString(id, count, obj);
    }

    public static boolean getBoolean(int id) {
        return app.getResources().getBoolean(id);
    }
}
