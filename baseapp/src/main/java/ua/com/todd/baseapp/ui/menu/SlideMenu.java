package ua.com.todd.baseapp.ui.menu;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewStub;

import ua.com.todd.baseapp.R;
import ua.com.todd.baseapp.ui.menu.config.MenuConfig;

public class SlideMenu implements ISlideMenu, MenuConfig.OnRefreshMenuConfig {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private MenuConfig config = new MenuConfig();
    private View leftMenuContainer;
    private View rightMenuContainer;

    public SlideMenu(Activity activity, int layoutResID, Toolbar toolbar) {
        activity.setContentView(R.layout.activity_base_slide_menu);
        ViewStub viewStub = (ViewStub) activity.findViewById(R.id.base_slide_content);
        viewStub.setLayoutResource(layoutResID);
        viewStub.inflate();
        mDrawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        leftMenuContainer = activity.findViewById(R.id.left_drawer);
        rightMenuContainer = activity.findViewById(R.id.right_drawer);
        mDrawerToggle = new ActionBarDrawerToggle(activity, mDrawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close) {

        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });
        config.setOnRefreshMenuConfig(this);
        config.refreshConfig();
    }

    public MenuConfig getMenuConfig() {
        return config;
    }

    @Override
    public void setMenuConfig(MenuConfig config) {
        config.setOnRefreshMenuConfig(this);
        setMenuType(config.getMenuType(), this.config.getMenuType());
        this.config = config;
    }

    private void setMenuType(MenuConfig.MenuType newType, MenuConfig.MenuType oldType) {
        switch (oldType) {
            case LEFT:
                switch (newType) {
                    case RIGHT:
                        mDrawerLayout.removeView(leftMenuContainer);
                    case LEFT_RIGHT:
                        mDrawerLayout.addView(rightMenuContainer);
                        break;
                }
                break;
            case RIGHT:
                switch (newType) {
                    case LEFT:
                        mDrawerLayout.removeView(rightMenuContainer);
                        break;
                    case LEFT_RIGHT:
                        mDrawerLayout.addView(leftMenuContainer);
                        break;
                }
                break;
            case LEFT_RIGHT:
                switch (newType) {
                    case LEFT:
                        mDrawerLayout.removeView(rightMenuContainer);
                        break;
                    case RIGHT:
                        mDrawerLayout.removeView(leftMenuContainer);
                        break;
                }
                break;
        }
    }

    public void syncState() {
        mDrawerToggle.syncState();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void toggle() {
        if (isMenuOpen()) {
            closeMenu();
        } else {
            if (config.getMenuType() == MenuConfig.MenuType.RIGHT)
                mDrawerLayout.openDrawer(Gravity.END);
            else
                mDrawerLayout.openDrawer(Gravity.START);
        }
    }

    @Override
    public void openLeftMenu() {
        mDrawerLayout.openDrawer(Gravity.START);
    }

    @Override
    public void openRightMenu() {
        mDrawerLayout.openDrawer(Gravity.END);
    }

    @Override
    public void closeLeftMenu() {
        mDrawerLayout.closeDrawer(Gravity.START);
    }

    @Override
    public void closeRightMenu() {
        mDrawerLayout.closeDrawer(Gravity.END);
    }

    @Override
    public void closeMenu() {
        mDrawerLayout.closeDrawers();
    }

    @Override
    public boolean isLeftMenuOpen() {
        return mDrawerLayout.isDrawerOpen(Gravity.START);
    }

    @Override
    public boolean isRightMenuOpen() {
        return mDrawerLayout.isDrawerOpen(Gravity.END);
    }

    @Override
    public boolean isMenuOpen() {
        return isLeftMenuOpen() || isRightMenuOpen();
    }
}
