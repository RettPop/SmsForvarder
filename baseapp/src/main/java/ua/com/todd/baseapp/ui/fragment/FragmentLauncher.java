package ua.com.todd.baseapp.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

import ua.com.todd.baseapp.BaseApplication;
import ua.com.todd.baseapp.R;
import ua.com.todd.baseapp.ui.fragment.FragmentFactory.IBaseFragmentType;

public class FragmentLauncher {
    private Activity activity;
    private BaseApplication app = BaseApplication.app();
    private FragmentFactory fragmentFactory = app.getFragmentFactory();
    private LinkedList<String> names = new LinkedList<String>();

    public FragmentLauncher(Activity activity) {
        names.push("");
        this.activity = activity;
    }

    public void addFragment(IBaseFragmentType type) {
        addFragment(type, null);
    }

    public void addFragmentWithStack(IBaseFragmentType type) {
        addFragmentWithStack(type, null);
    }

    public void replaceFragment(FragmentFactory.IBaseFragmentType type) {
        replaceFragment(type, null);
    }

    public void replaceFragmentWithStack(IBaseFragmentType type) {
        replaceFragmentWithStack(type, null);
    }

    public void addFragment(IBaseFragmentType type, Bundle bundle) {
        launchFragment(0, type, false, LaunchType.ADD, bundle);
    }

    public void addFragmentWithStack(IBaseFragmentType type, Bundle bundle) {
        launchFragment(0, type, true, LaunchType.ADD, bundle);
    }

    public void replaceFragment(IBaseFragmentType type, Bundle bundle) {
        launchFragment(0, type, false, LaunchType.REPLACE, bundle);
    }

    public void replaceFragmentWithStack(IBaseFragmentType type, Bundle bundle) {
        launchFragment(0, type, true, LaunchType.REPLACE, bundle);
    }

    public void addFragment(int containerId, IBaseFragmentType type) {
        addFragment(containerId, type, null);
    }

    public void addFragmentWithStack(int containerId, IBaseFragmentType type) {
        addFragmentWithStack(containerId, type, null);
    }

    public void replaceFragment(int containerId, IBaseFragmentType type) {
        replaceFragment(containerId, type, null);
    }

    public void replaceFragmentWithStack(int containerId, IBaseFragmentType type) {
        replaceFragmentWithStack(containerId, type, null);
    }

    public void addFragment(int containerId, IBaseFragmentType type, Bundle bundle) {
        launchFragment(containerId, type, false, LaunchType.ADD, bundle);
    }

    public void addFragmentWithStack(int containerId, IBaseFragmentType type, Bundle bundle) {
        launchFragment(containerId, type, true, LaunchType.ADD, bundle);
    }

    public void replaceFragment(int containerId, IBaseFragmentType type, Bundle bundle) {
        launchFragment(containerId, type, false, LaunchType.REPLACE, bundle);
    }

    public void replaceFragmentWithStack(int containerId, IBaseFragmentType type, Bundle bundle) {
        launchFragment(containerId, type, true, LaunchType.REPLACE, bundle);
    }

    public FragmentLauncher clearStack(){
        FragmentManager fm = activity.getFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            names.pop();
            fm.popBackStack();
        }
        return this;
    }

    private void launchFragment(int containerId, IBaseFragmentType type, boolean backStack, LaunchType launchType, Bundle bundle) {
        String name = type.toString();
        String topFragmentName = names.getFirst();
        if (name.equals(topFragmentName))
            return;
        Fragment fragment = activity.getFragmentManager().findFragmentByTag(type.toString());
        if (fragment == null) {
            fragment = fragmentFactory.getFragment(type, bundle);
        }
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        switch (launchType) {

            case ADD:
                transaction.add(containerId == 0 ? R.id.container : containerId,
                        fragment, name);
                break;
            case REPLACE:
                transaction.replace(containerId == 0 ? R.id.container : containerId,
                        fragment, name);
                break;
        }
        if (backStack)
            transaction.addToBackStack(name);
        names.remove(name);
        names.push(name);
        transaction.commit();
    }

    enum LaunchType {
        ADD, REPLACE
    }
}
