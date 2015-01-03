package ua.com.todd.smsforwading.fragment

import ua.com.todd.baseapp.ui.fragment.FragmentFactory.IBaseFragmentType
import android.os.Bundle
import android.app.Fragment
import ua.com.todd.baseapp.ui.fragment

public class FragmentFactory : fragment.FragmentFactory() {
    override fun getFragment(type: IBaseFragmentType, bundle: Bundle?) = when (type as FragmentType) {
        FragmentType.SETTINGS -> SettingsFragment.getInstance()
        FragmentType.PROFILE -> ProfileFragment.getInstance()
        FragmentType.ITEM -> AddItemFragment.getInstance()
    }

    public enum class FragmentType : IBaseFragmentType {
        SETTINGS
        PROFILE
        ITEM
    }
}
