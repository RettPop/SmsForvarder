package ua.com.todd.baseapp.fragment;

import android.app.Fragment;
import android.os.Bundle;

public abstract class FragmentFactory {
    public Fragment getFragment(BaseFragmentType type){
        return getFragment(type, null);
    }

    public abstract Fragment getFragment(BaseFragmentType type, Bundle bundle);

    public interface BaseFragmentType {}
}
