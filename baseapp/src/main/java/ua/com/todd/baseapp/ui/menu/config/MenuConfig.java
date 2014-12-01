package ua.com.todd.baseapp.ui.menu.config;

public class MenuConfig {
    private OnRefreshMenuConfig onRefreshMenuConfig;
    private MenuType menuType = MenuType.NONE;
    private int leftLayoutId;
    private int rightLayoutId;

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

    public void refreshConfig() {
        if (onRefreshMenuConfig != null)
            onRefreshMenuConfig.setMenuConfig(this);
    }

    public int getLeftLayoutId() {
        return leftLayoutId;
    }

    public MenuConfig setLeftLayoutId(int leftLayoutId) {
        this.leftLayoutId = leftLayoutId;
        return this;
    }

    public int getRightLayoutId() {
        return rightLayoutId;
    }

    public MenuConfig setRightLayoutId(int rightLayoutId) {
        this.rightLayoutId = rightLayoutId;
        return this;
    }

    public enum MenuType {
        LEFT, RIGHT, LEFT_RIGHT, NONE
    }

    public interface OnRefreshMenuConfig {
        public void setMenuConfig(MenuConfig menuConfig);
    }


}
