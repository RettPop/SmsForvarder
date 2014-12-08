package ua.com.todd.baseapp.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewStub;

import ua.com.todd.baseapp.R;
import ua.com.todd.baseapp.ui.menu.ISlideMenu;
import ua.com.todd.baseapp.ui.menu.SlideMenu;
import ua.com.todd.baseapp.ui.menu.config.MenuConfig;

public class SlideMenuBaseActivity extends BaseActivity implements ISlideMenu {

    private SlideMenu slideMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        slideMenu = new SlideMenu(this, getToolbar());
    }

    @Override
    void setBaseContentView(int layoutResID) {
        super.setBaseContentView(R.layout.menu_container);
        ViewStub viewStub = (ViewStub) findViewById(R.id.base_menu_content);
        viewStub.setLayoutResource(layoutResID);
        viewStub.inflate();
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

    @Override
    public void onBackPressed()
    {
        if (isMenuOpen()) {
            toggle();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void toggle() {
        slideMenu.toggle();
    }

    @Override
    public void openLeftMenu() {
        slideMenu.openLeftMenu();
    }

    @Override
    public void openRightMenu() {
        slideMenu.openRightMenu();
    }

    @Override
    public void closeLeftMenu() {
        slideMenu.closeLeftMenu();
    }

    @Override
    public void closeRightMenu() {
        slideMenu.closeRightMenu();
    }

    @Override
    public void closeMenu() {
        slideMenu.closeMenu();
    }

    @Override
    public boolean isLeftMenuOpen() {
        return slideMenu.isLeftMenuOpen();
    }

    @Override
    public boolean isRightMenuOpen() {
        return slideMenu.isRightMenuOpen();
    }

    @Override
    public boolean isMenuOpen() {
        return slideMenu.isMenuOpen();
    }

    @Override
    public MenuConfig getMenuConfig() {
        return slideMenu.getMenuConfig();
    }
}
