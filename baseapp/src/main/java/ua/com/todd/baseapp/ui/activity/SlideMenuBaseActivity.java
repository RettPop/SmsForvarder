package ua.com.todd.baseapp.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;

import ua.com.todd.baseapp.R;
import ua.com.todd.baseapp.ui.menu.SlideMenu;
import ua.com.todd.baseapp.ui.menu.config.MenuConfig;

public abstract class SlideMenuBaseActivity extends BaseActivity {

    private SlideMenu slideMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        slideMenu = new SlideMenu(this, getToolbar());
    }

    @Override
    void setBaseContentView(int layoutResID) {
        super.setBaseContentView(R.layout.menu_container);
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent e) {
        switch (keycode) {
            case KeyEvent.KEYCODE_MENU:
                slideMenu.toggle();
                return true;
        }
        return super.onKeyDown(keycode, e);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        slideMenu.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        slideMenu.onConfigurationChanged(newConfig);
     }
}
