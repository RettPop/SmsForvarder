package ua.com.todd.baseapp.ui.menu.config;

public class MenuConfig {
    private OnRefreshMenuConfig onRefreshMenuConfig;
    private MenuType menuType = MenuType.LEFT_RIGHT;

    public void setOnRefreshMenuConfig(OnRefreshMenuConfig onRefreshMenuConfig) {
        this.onRefreshMenuConfig = onRefreshMenuConfig;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public MenuConfig setMenuType(MenuType menuType) {
        this.menuType = menuType;
        return this;
    }

    public void refreshConfig(){
        if(onRefreshMenuConfig != null)
            onRefreshMenuConfig.setMenuConfig(this);
    }

    public enum MenuType{
        LEFT, RIGHT, LEFT_RIGHT
    }

    public interface OnRefreshMenuConfig{
        public void setMenuConfig(MenuConfig menuConfig);
    }
}
