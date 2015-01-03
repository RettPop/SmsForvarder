package ua.com.todd.smsforwading.fragment

import ua.com.todd.baseapp.ui.fragment.BaseFragment
import ua.com.todd.baseapp.ui.activity.LayoutId
import ua.com.todd.smsforwading.R
import android.app.Fragment
import de.greenrobot.event.EventBus

[LayoutId(R.layout.fragment_item)]
public class AddItemFragment : BaseFragment(){
    class object {
        public fun getInstance(): Fragment = AddItemFragment()
    }
}
