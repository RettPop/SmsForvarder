package ua.com.todd.smsforwading.fragment

import ua.com.todd.baseapp.fragment.FragmentFactory.BaseFragmentType
import android.os.Bundle
import android.app.Fragment

public class FragmentFactory : ua.com.todd.baseapp.fragment.FragmentFactory() {
    override fun getFragment(type: BaseFragmentType, bundle: Bundle?): Fragment {
        val fragmentType = type as FragmentType;
        return when (fragmentType){
            FragmentType.SETTINGS -> SettingsFragment()
        }
    }

    public enum class FragmentType : BaseFragmentType {
        SETTINGS
    }
}
