package ua.com.todd.smsforwading.fragment

import ua.com.todd.baseapp.fragment.BaseFragment
import android.app.Fragment
import ua.com.todd.smsforwading.R

public class ProfileFragment : BaseFragment(){
    class object {
        public fun getInstance(): Fragment = ProfileFragment()
    }

    override fun getLayoutId() = R.layout.fragment_profile
}