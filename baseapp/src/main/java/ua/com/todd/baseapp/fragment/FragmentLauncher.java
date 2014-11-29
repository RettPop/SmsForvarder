package ua.com.todd.baseapp.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import ua.com.todd.baseapp.BaseApplication;
import ua.com.todd.baseapp.R;
import ua.com.todd.baseapp.fragment.FragmentFactory.BaseFragmentType;

public class FragmentLauncher {
    private Activity activity;
    private BaseApplication app = BaseApplication.getInstance();
    private FragmentFactory fragmentFactory = app.getFragmentFactory();

    public FragmentLauncher(Activity activity) {
        this.activity = activity;
    }

    public void addFragment(BaseFragmentType type) {
        addFragment(type, null);
    }

    public void addFragmentWithStack(BaseFragmentType type) {
        addFragmentWithStack(type, null);
    }

    public void replaceFragment(BaseFragmentType type) {
        replaceFragment(type, null);
    }

    public void replaceFragmentWithStack(BaseFragmentType type) {
        replaceFragmentWithStack(type, null);
    }

    public void addFragment(BaseFragmentType type, Bundle bundle) {
        launchFragment(0, type, false, LaunchType.ADD, bundle);
    }

    public void addFragmentWithStack(BaseFragmentType type, Bundle bundle) {
        launchFragment(0, type, true, LaunchType.ADD, bundle);
    }

    public void replaceFragment(FragmentFactory.BaseFragmentType type, Bundle bundle) {
        launchFragment(0, type, false, LaunchType.REPLACE, bundle);
    }

    public void replaceFragmentWithStack(BaseFragmentType type, Bundle bundle) {
        launchFragment(0, type, true, LaunchType.REPLACE, bundle);
    }

    public void addFragment(int containerId, BaseFragmentType type) {
        addFragment(containerId, type, null);
    }

    public void addFragmentWithStack(int containerId, BaseFragmentType type) {
        addFragmentWithStack(containerId, type, null);
    }

    public void replaceFragment(int containerId, BaseFragmentType type) {
        replaceFragment(containerId, type, null);
    }

    public void replaceFragmentWithStack(int containerId, BaseFragmentType type) {
        replaceFragmentWithStack(containerId, type, null);
    }

    public void addFragment(int containerId, BaseFragmentType type, Bundle bundle) {
        launchFragment(containerId, type, false, LaunchType.ADD, bundle);
    }

    public void addFragmentWithStack(int containerId, BaseFragmentType type, Bundle bundle) {
        launchFragment(containerId, type, true, LaunchType.ADD, bundle);
    }

    public void replaceFragment(int containerId, BaseFragmentType type, Bundle bundle) {
        launchFragment(containerId, type, false, LaunchType.REPLACE, bundle);
    }

    public void replaceFragmentWithStack(int containerId, BaseFragmentType type, Bundle bundle) {
        launchFragment(containerId, type, true, LaunchType.REPLACE, bundle);
    }

    private void launchFragment(int containerId, BaseFragmentType type, boolean backStack, LaunchType launchType, Bundle bundle) {
        Fragment fragment = activity.getFragmentManager().findFragmentByTag(type.toString());
        if (fragment == null) {
            fragment = fragmentFactory.getFragment(type, bundle);
        }
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        switch (launchType) {

            case ADD:
                transaction.add(containerId == 0 ? R.id.container : containerId,
                        fragment, type.toString());
                break;
            case REPLACE:
                transaction.replace(containerId == 0 ? R.id.container : containerId,
                        fragment, type.toString());
                break;
        }
        if (backStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    enum LaunchType {
        ADD, REPLACE
    }
}
