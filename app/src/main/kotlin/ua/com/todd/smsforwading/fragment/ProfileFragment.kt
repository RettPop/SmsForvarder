package ua.com.todd.smsforwading.fragment

import ua.com.todd.baseapp.ui.fragment.BaseFragment
import android.app.Fragment
import ua.com.todd.smsforwading.R
import ua.com.todd.baseapp.ui.activity.LayoutId

[LayoutId(R.layout.fragment_profile)]
public class ProfileFragment : BaseFragment(){
    class object {
        public fun getInstance(): Fragment = ProfileFragment()
    }
}