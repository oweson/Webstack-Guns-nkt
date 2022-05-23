package json;

/**
 * @Author cheng
 * @Date 2022/5/23 10:43
 */
public class SiteList {
    private int siteId;
    private String siteName;
    private int menuId;
    private String sitePath;
    private String siteDescription;
    private String siteUrl;
    private int orderNum;
    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }
    public int getSiteId() {
        return siteId;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    public String getSiteName() {
        return siteName;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
    public int getMenuId() {
        return menuId;
    }

    public void setSitePath(String sitePath) {
        this.sitePath = sitePath;
    }
    public String getSitePath() {
        return sitePath;
    }

    public void setSiteDescription(String siteDescription) {
        this.siteDescription = siteDescription;
    }
    public String getSiteDescription() {
        return siteDescription;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }
    public String getSiteUrl() {
        return siteUrl;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
    public int getOrderNum() {
        return orderNum;
    }
}
