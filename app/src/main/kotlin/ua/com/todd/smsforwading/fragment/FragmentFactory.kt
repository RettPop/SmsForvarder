package ua.com.todd.smsforwading.fragment

import ua.com.todd.baseapp.fragment.FragmentFactory.IBaseFragmentType
import android.os.Bundle
import android.app.Fragment

public class FragmentFactory : ua.com.todd.baseapp.fragment.FragmentFactory() {
    override fun getFragment(type: IBaseFragmentType, bundle: Bundle?) = when (type as FragmentType) {
        FragmentType.SETTINGS -> SettingsFragment()
    }

    public enum class FragmentType : IBaseFragmentType {
        SETTINGS
    }
}
