package ua.com.todd.smsforwading.activity

import ua.com.todd.smsforwading.R
import ua.com.todd.baseapp.ui.activity.SlideMenuBaseActivity
import ua.com.todd.baseapp.ui.activity.LayoutId
import android.os.Bundle
import ua.com.todd.baseapp.ui.menu.config.MenuConfig
import ua.com.todd.smsforwading.model.MenuEvent
import ua.com.todd.smsforwading.MenuItemType
import ua.com.todd.smsforwading.fragment.FragmentFactory
import android.widget.Switch
import ua.com.todd.smsforwading.MyApplication
import ua.com.todd.smsforwading.managers.PreferenceManager

[LayoutId(R.layout.activity_main)]
public class MainActivity : SlideMenuBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super<SlideMenuBaseActivity>.onCreate(savedInstanceState)
        val pref = MyApplication.app().getPreferenceManager<PreferenceManager>()
        getMenuConfig()
                .setLeftLayoutId(R.layout.layout_menu)
                .setMenuType(MenuConfig.MenuType.LEFT)
                .setToolbarType(MenuConfig.ToolbarType.UP_BTB)
                .refreshConfig()
        getFragmentManager().beginTransaction()
                .add(R.id.container, getFragment(FragmentFactory.FragmentType.PROFILE))
                .commit()
        addToolbarView(R.layout.toolbar_view)
        val isEnable = pref.isEnabled()
        val switch = findViewById(R.id.switch_enable) as Switch
        switch.setChecked(isEnable)
        switch.setOnCheckedChangeListener {(compoundButton, b) ->
            pref.storeEnabled(b)
        }
    }

    public fun onEvent(event: MenuEvent) {
        when (MenuItemType.getType(event.id)) {
            MenuItemType.SETTINGS -> {
                closeMenu()
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, getFragment(FragmentFactory.FragmentType.SETTINGS))
                        .commit()
            }
            MenuItemType.PROFILE -> {
                closeMenu()
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, getFragment(FragmentFactory.FragmentType.PROFILE))
                        .commit()
            }
        }
    }
}
