package ua.com.todd.baseapp.fragment;

import android.app.Fragment;
import android.os.Bundle;

public abstract class FragmentFactory {
    public Fragment getFragment(IBaseFragmentType type){
        return getFragment(type, null);
    }

    public abstract Fragment getFragment(IBaseFragmentType type, Bundle bundle);

    public interface IBaseFragmentType {}
}
