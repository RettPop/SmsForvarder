package ua.com.todd.smsforwading.activity

import ua.com.todd.smsforwading.R
import ua.com.todd.baseapp.ui.activity.SlideMenuBaseActivity
import ua.com.todd.baseapp.ui.activity.LayoutId
import android.os.Bundle
import ua.com.todd.baseapp.ui.menu.config.MenuConfig
import de.greenrobot.event.EventBus
import ua.com.todd.smsforwading.model.MenuEvent
import ua.com.todd.smsforwading.MenuItemType
import ua.com.todd.smsforwading.fragment.FragmentFactory
import ua.com.todd.smsforwading.fragment.ProfileFragment
import ua.com.todd.smsforwading.fragment.SettingsFragment

[LayoutId(R.layout.activity_main)]
public class MainActivity : SlideMenuBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super<SlideMenuBaseActivity>.onCreate(savedInstanceState)
        getMenuConfig()
                .setLeftLayoutId(R.layout.layout_menu)
                .setMenuType(MenuConfig.MenuType.LEFT)
                .refreshConfig()
    }

    override fun onResume() {
        super<SlideMenuBaseActivity>.onResume()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        super<SlideMenuBaseActivity>.onPause()
        EventBus.getDefault().unregister(this)
    }

    public fun onEvent(event: MenuEvent) {
        when (MenuItemType.getType(event.id)) {
            MenuItemType.SETTINGS -> {
                closeMenu()
                getFragmentLauncher()
                        .addFragmentWithStack(FragmentFactory.FragmentType.SETTINGS)
            }
        }
    }
}
