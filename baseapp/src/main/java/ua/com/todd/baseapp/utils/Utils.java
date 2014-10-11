package ua.com.todd.baseapp.utils;

public class Utils {
    public static boolean isNotNull(Object... obj) {
        boolean isNotNull = true;
        if (obj == null) {
            return false;
        }
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] == null) {
                isNotNull = false;
                break;
            }
        }
        return isNotNull;
    }
}
