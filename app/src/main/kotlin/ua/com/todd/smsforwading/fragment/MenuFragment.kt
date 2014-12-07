package ua.com.todd.smsforwading.fragment

import android.app.Fragment
import ua.com.todd.baseapp.ui.fragment.BaseFragment
import ua.com.todd.smsforwading.R
import ua.com.todd.baseapp.ui.activity.LayoutId
import ua.com.todd.baseapp.ui.fragment.BaseMenuFragment
import de.greenrobot.event.EventBus
import ua.com.todd.smsforwading.model.MenuEvent

[LayoutId(R.layout.fragment_menu)]
public class MenuFragment : BaseMenuFragment(){

    override fun onMenuItemClick(id: Long) {
        EventBus.getDefault().post(MenuEvent(id))
    }
}