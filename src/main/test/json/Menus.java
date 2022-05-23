package json;

import java.util.List;

/**
 * @Author cheng
 * @Date 2022/5/23 10:44
 */
public class Menus {
    private int menuId;
    private String menuName;
    private String menuIcon;
    private int parentId;
    private List<String> children;
    private String siteList;
    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
    public int getMenuId() {
        return menuId;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getMenuName() {
        return menuName;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }
    public String getMenuIcon() {
        return menuIcon;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
    public int getParentId() {
        return parentId;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }
    public List<String> getChildren() {
        return children;
    }

    public void setSiteList(String siteList) {
        this.siteList = siteList;
    }
    public String getSiteList() {
        return siteList;
    }

}
