package ua.com.todd.baseapp.managers;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Set;

public class BasePreferenceManager<T extends Application> extends BaseManager<T> {
    private final SharedPreferences sp;

    public BasePreferenceManager(T app) {
        super(app);
        sp = app.getSharedPreferences(app.getPackageName(), Context.MODE_PRIVATE);
    }

    protected void storeString(IKey key, String str) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key.toString(), str);
        editor.apply();
    }

    protected String restoreString(IKey key) {
        return sp.getString(key.toString(), "");
    }

    protected void storeBoolean(IKey key, boolean b) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key.toString(), b);
        editor.apply();
    }

    protected boolean restoreBoolean(IKey key) {
        return sp.getBoolean(key.toString(), true);
    }

    protected void storeInt(IKey key, int i) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key.toString(), i);
        editor.apply();
    }

    protected int restoreInt(IKey key) {
        return sp.getInt(key.toString(), 0);
    }

    protected void storeFloat(IKey key, float i) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key.toString(), i);
        editor.apply();
    }

    protected float restoreFloat(IKey key) {
        return sp.getFloat(key.toString(), 0);
    }

    protected void storeLong(IKey key, long i) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key.toString(), i);
        editor.apply();
    }

    protected long restoreLong(IKey key) {
        return sp.getLong(key.toString(), 0);
    }

    protected void storeStringSet(IKey key, Set<String> i) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet(key.toString(), i);
        editor.apply();
    }

    protected Set<String> restoreStringSet(IKey key) {
        return sp.getStringSet(key.toString(), new HashSet<String>());
    }

    protected <T> void storeObject(IKey key, T obj) {
        Gson gson = new Gson();
        String str = gson.toJson(obj);
        storeString(key, str);
    }

    protected <T> T restoreObject(IKey key, Class<T> cls) {
        T obj = null;
        String str = restoreString(key);
        if (str != null) {
            Gson gson = new Gson();
            obj = gson.fromJson(str, cls);
        }
        return obj;
    }

    protected interface IKey {
    }

}
