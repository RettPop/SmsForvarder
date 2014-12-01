package ua.com.todd.smsforwading.activity

import ua.com.todd.smsforwading.R
import ua.com.todd.baseapp.ui.activity.SlideMenuBaseActivity
import ua.com.todd.baseapp.ui.activity.LayoutId
import android.os.Bundle
import ua.com.todd.baseapp.ui.menu.config.MenuConfig

[LayoutId(R.layout.activity_main)]
public class MainActivity : SlideMenuBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super<SlideMenuBaseActivity>.onCreate(savedInstanceState)
        getMenuConfig()
                .setLeftLayoutId(R.layout.layout_menu)
                .setMenuType(MenuConfig.MenuType.LEFT)
                .refreshConfig()
    }
}
