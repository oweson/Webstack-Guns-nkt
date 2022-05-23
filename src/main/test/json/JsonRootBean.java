package json;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author cheng
 * @Date 2022/5/23 10:42
 */
public class JsonRootBean {
    public static void main(String[] args) {
        jsonDeal();
    }

    public static Map<Menus, List<Sites>>  jsonDeal() {
        String jsonString = "";
        File file = new File("D:\\jj.json");
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            int size = fileInputStream.available();
            byte[] buffer = new byte[size];
            fileInputStream.read(buffer);
            jsonString = new String(buffer, StandardCharsets.UTF_8);
            jsonString.replaceAll("\n", "");
            jsonString.replaceAll("\r", "");
            JSONObject json = JSON.parseObject(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String jsonStr = jsonString;


        JsonRootBean jsonRootBean = JSON.parseObject(jsonStr, JsonRootBean.class);
        // 站点
        List<Sites> sites = jsonRootBean.getSites();

        //匹配:  k:parentId,v：子网站集合

        Map<Integer, List<Sites>> linkMaps = new LinkedHashMap<>();
        Map<Menus, List<Sites>> menuAndSiteMaps = new LinkedHashMap<>(1042);
        Map<Integer, List<Sites>> siteMaps = sites.stream().collect(Collectors.groupingBy(x -> x.getParentId()));

        List<Sites> firstLevelOnly = sites.stream().filter(x -> Objects.equals(0, x.getParentId())).collect(Collectors.toList());

        Map<Integer, List<Sites>> firstLevelMaps = firstLevelOnly.stream().collect(Collectors.groupingBy(x -> x.getMenuId()));

        //siteMaps.putAll(firstLevelMaps);
        linkMaps.putAll(firstLevelMaps);
        linkMaps.putAll(siteMaps);
        List<Menus> menus = jsonRootBean.getMenus();
        for (Menus menu : menus) {
            if (linkMaps.containsKey(menu.getMenuId())) {
                menuAndSiteMaps.put(menu, linkMaps.get(menu.getMenuId()));
            }
        }
        if (CollectionUtil.isNotEmpty(menuAndSiteMaps)) {
            System.out.println(menuAndSiteMaps);

          /*  for (Map.Entry<Menus, List<Sites>> entry : menuAndSiteMaps.entrySet()) {
                Menus key = entry.getKey();
                List<Sites> value = entry.getValue();
                System.out.println(key);
                System.out.println(value);
                System.out.println("assignValue  end!");


                // 1 初始化以及菜单

                // 2 1->2-3绑定！
                // todo 重复的干掉！  保留最早的   url+name唯一确认！

            }*/
        }


        // 匹配

        for (Sites site : sites) {
            // 子网
            List<SiteList> siteList = site.getSiteList();
            for (SiteList list : siteList) {

                System.out.println(list);
            }
        }

        return menuAndSiteMaps;

    }

    private String msg;
    private int code;
    private boolean system;
    private List<Sites> sites;
    private List<Menus> menus;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

    public boolean getSystem() {
        return system;
    }

    public void setSites(List<Sites> sites) {
        this.sites = sites;
    }

    public List<Sites> getSites() {
        return sites;
    }

    public void setMenus(List<Menus> menus) {
        this.menus = menus;
    }

    public List<Menus> getMenus() {
        return menus;
    }
}
