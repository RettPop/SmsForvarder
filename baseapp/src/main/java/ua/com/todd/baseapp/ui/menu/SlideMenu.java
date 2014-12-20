package ua.com.todd.baseapp.ui.menu;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

import ua.com.todd.baseapp.R;
import ua.com.todd.baseapp.ui.menu.config.MenuConfig;
import ua.com.todd.baseapp.utils.AndroidUtils;

public class SlideMenu implements ISlideMenu, MenuConfig.OnRefreshMenuConfig {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private MenuConfig config = new MenuConfig();
    private ViewGroup leftMenuContainer;
    private ViewGroup rightMenuContainer;
    private Activity activity;
    private Toolbar toolbar;
    private ViewGroup content;
    private ViewGroup baseMenuContent;

    public SlideMenu(Activity activity) {
        this(activity, null);
    }

    public SlideMenu(Activity activity, Toolbar toolbar) {
        this.activity = activity;
        this.toolbar = toolbar;
        View view = View.inflate(activity, R.layout.activity_base_slide_menu, null);
        content = (ViewGroup) activity.findViewById(android.R.id.content);
        View contentView = content.getChildAt(0);
        content.removeView(contentView);
        content.addView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        baseMenuContent = (ViewGroup) view.findViewById(R.id.base_menu_content);
        baseMenuContent.addView(contentView);
        mDrawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        leftMenuContainer = (ViewGroup) activity.findViewById(R.id.left_drawer);
        rightMenuContainer = (ViewGroup) activity.findViewById(R.id.right_drawer);
        if (toolbar == null) {
            mDrawerToggle = new ActionBarDrawerToggle(activity, mDrawerLayout,
                    R.string.drawer_open, R.string.drawer_close) {

            };
        } else {
            mDrawerToggle = new ActionBarDrawerToggle(activity, mDrawerLayout, toolbar,
                    R.string.drawer_open, R.string.drawer_close) {

            };
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggle();
                }
            });
        }

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        config.setOnRefreshMenuConfig(this);
        config.refreshConfig();
    }

    @Override
    public MenuConfig getMenuConfig() {
        return config;
    }

    @Override
    public void setMenuConfig(final MenuConfig config) {
        config.setOnRefreshMenuConfig(this);
        setMenuType(config.getMenuType(), this.config.getMenuType());
        this.config = config;

        int idLeft = config.getLeftLayoutId();
        if (idLeft != 0)
            AndroidUtils.injectSingleViewInLayout(leftMenuContainer, idLeft);
        int idRight = config.getRightLayoutId();
        if (idRight != 0)
            AndroidUtils.injectSingleViewInLayout(rightMenuContainer, idRight);
        if (toolbar != null && config.getToolbarType() != MenuConfig.ToolbarType.DEFAULT) {
            toolbar.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            toolbar.layout(0, 0,
                    toolbar.getMeasuredWidth(),
                    toolbar.getMeasuredHeight());
            ((ViewGroup) toolbar.getParent()).removeView(toolbar);
            FrameLayout toolbarContainer = (FrameLayout) activity.findViewById(R.id.base_toolbar);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                    toolbar.getLayoutParams().width, toolbar.getLayoutParams().height
            );
            DrawerLayout.LayoutParams baseLayout = (DrawerLayout.LayoutParams) baseMenuContent.getLayoutParams();
            DrawerLayout.LayoutParams leftLayout = (DrawerLayout.LayoutParams) leftMenuContainer.getLayoutParams();
            DrawerLayout.LayoutParams rightLayout = (DrawerLayout.LayoutParams) rightMenuContainer.getLayoutParams();
            switch (config.getToolbarType()) {
                case UP_BTB:
                    baseLayout.setMargins(0, toolbar.getMeasuredHeight(), 0, 0);
                case UP:
                    layoutParams.gravity = Gravity.TOP;
                    leftLayout.setMargins(0, toolbar.getMeasuredHeight(), 0, 0);
                    rightLayout.setMargins(0, toolbar.getMeasuredHeight(), 0, 0);
                    toolbarContainer.addView(toolbar, layoutParams);
                    break;
                case DOWN_BTB:
                    baseLayout.setMargins(0, 0, 0, toolbar.getMeasuredHeight());
                case DOWN:
                    layoutParams.gravity = Gravity.BOTTOM;
                    leftLayout.setMargins(0, 0, 0, toolbar.getMeasuredHeight());
                    rightLayout.setMargins(0, 0, 0, toolbar.getMeasuredHeight());
                    toolbarContainer.addView(toolbar, layoutParams);
                    break;
                case DEFAULT:
                    break;
            }
        }
    }

    private void setMenuType(MenuConfig.MenuType newType, MenuConfig.MenuType oldType) {
        switch (oldType) {
            case LEFT:
                switch (newType) {
                    case NONE:
                    case RIGHT:
                        mDrawerLayout.removeView(leftMenuContainer);
                    case LEFT_RIGHT:
                        mDrawerLayout.addView(rightMenuContainer);
                        break;
                    default:
                        mDrawerLayout.addView(leftMenuContainer);
                }
                break;
            case RIGHT:
                switch (newType) {
                    case NONE:
                    case LEFT:
                        mDrawerLayout.removeView(rightMenuContainer);
                        break;
                    case LEFT_RIGHT:
                        mDrawerLayout.addView(leftMenuContainer);
                        break;
                    default:
                        mDrawerLayout.addView(rightMenuContainer);
                }
                break;
            case LEFT_RIGHT:
                switch (newType) {
                    case LEFT:
                        mDrawerLayout.removeView(rightMenuContainer);
                        break;
                    case NONE:
                        mDrawerLayout.removeView(rightMenuContainer);
                    case RIGHT:
                        mDrawerLayout.removeView(leftMenuContainer);
                        break;
                    default:
                        mDrawerLayout.addView(rightMenuContainer);
                        mDrawerLayout.addView(leftMenuContainer);
                }
                break;
            case NONE:
                switch (newType) {
                    case LEFT:
                        mDrawerLayout.addView(leftMenuContainer);
                        break;
                    case LEFT_RIGHT:
                        mDrawerLayout.removeView(leftMenuContainer);
                    case RIGHT:
                        mDrawerLayout.removeView(rightMenuContainer);
                        break;
                    default:
                        mDrawerLayout.removeView(rightMenuContainer);
                        mDrawerLayout.removeView(leftMenuContainer);
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
            switch (config.getMenuType()) {

                case RIGHT:
                    mDrawerLayout.openDrawer(Gravity.END);
                    break;
                case LEFT_RIGHT:
                case LEFT:
                    mDrawerLayout.openDrawer(Gravity.START);
                    break;
            }
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
