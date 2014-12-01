package ua.com.todd.baseapp.utils;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

import ua.com.todd.baseapp.BaseApplication;

public class AndroidUtils {

    private static BaseApplication app = BaseApplication.getInstance();

    public static void injectSingleViewInLayout(ViewGroup viewGroup, int id) {
        if (viewGroup.getChildCount() > 1) {
            View leftView = viewGroup.getChildAt(0);
            if (leftView.getId() != id) {
                viewGroup.removeView(leftView);
                injectViewInLayout(viewGroup, id);
            }
        } else {
            injectViewInLayout(viewGroup, id);
        }
    }

    public static void injectViewInLayout(ViewGroup viewGroup, int id) {
        View view = View.inflate(viewGroup.getContext(), id, null);
        view.setId(id);
        viewGroup.addView(view);
    }

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
