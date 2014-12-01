package ua.com.todd.baseapp.ui.menu;

import ua.com.todd.baseapp.ui.menu.config.MenuConfig;

public interface ISlideMenu {
    public void toggle();

    public void openLeftMenu();

    public void openRightMenu();

    public void closeLeftMenu();

    public void closeRightMenu();

    public void closeMenu();

    public boolean isLeftMenuOpen();

    public boolean isRightMenuOpen();

    public boolean isMenuOpen();

    public MenuConfig getMenuConfig();
}
