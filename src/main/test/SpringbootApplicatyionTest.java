import cn.hutool.http.HttpUtil;
import com.google.common.collect.Lists;
import com.nikati.manage.WebstackGunsApplication;
import com.nikati.manage.modular.system.dao.CategoryMapper;
import com.nikati.manage.modular.system.dao.SiteMapper;
import com.nikati.manage.modular.system.dao.VisitorMapper;
import com.nikati.manage.modular.system.model.Category;
import com.nikati.manage.modular.system.model.Site;
import com.nikati.manage.modular.system.model.Visitor;
import json.JsonRootBean;
import json.Menus;
import json.SiteList;
import json.Sites;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebstackGunsApplication.class)
public class SpringbootApplicatyionTest {
    @Autowired
    private VisitorMapper visitorMapper;

    @Autowired
    private SiteMapper siteMapper;

    @Autowired

    private CategoryMapper categoryMapper;





    @Test

    public void jsonDealTest(){
        Map<Menus, List<Sites>> menusListMap = JsonRootBean.jsonDeal();
        for (Map.Entry<Menus, List<Sites>> entry : menusListMap.entrySet()) {
            Integer pID=78;
            Menus key = entry.getKey();
            List<Sites> value = entry.getValue();
            // 1 插入二级分类
            Category category = new Category();
           // category.setId(0);
            category.setParentId(0);
            category.setSort(4);
            category.setTitle(key.getMenuName());
            category.setIcon("fa-align-right");
            category.setLevels(1);
            //category.setSites(Lists.newArrayList());
            category.setCreateTime("");
            category.setUpdateTime("");
            int insert = categoryMapper.insertSelective(category);
            if(value.size()==1){
                // 一个集合
                List<SiteList> siteList = value.get(0).getSiteList();
                for (SiteList site : siteList) {
                    Site bean = new Site();
                    bean.setState(1);
                    bean.setCategoryId(category.getId());
                    bean.setTitle(site.getSiteName());
                    bean.setCategoryTitle("");
                    bean.setThumb("");
                    bean.setDescription(site.getSiteDescription());
                    bean.setUrl(site.getSiteUrl());
                    bean.setCreateTime("");
                    bean.setUpdateTime("");
                    siteMapper.insertSelective(bean);
                }
            }
            else{
                for (Sites keySon : value) {
                    Category categorySon = new Category();
                    // category.setId(0);
                    categorySon.setParentId(category.getId());
                    categorySon.setSort(4);
                    categorySon.setTitle(keySon.getMenuName());
                    categorySon.setIcon("fa-align-right");
                    categorySon.setLevels(2);
                    //category.setSites(Lists.newArrayList());
                    categorySon.setCreateTime("");
                    categorySon.setUpdateTime("");
                    categoryMapper.insertSelective(categorySon);

                    List<SiteList> siteList = keySon.getSiteList();
                    for (SiteList item : siteList) {
                        Site bean = new Site();
                        bean.setState(1);
                        bean.setCategoryId(categorySon.getId());
                        bean.setTitle(item.getSiteName());
                        bean.setCategoryTitle("");
                        bean.setThumb("");
                        bean.setDescription(item.getSiteDescription());
                        bean.setUrl(item.getSiteUrl());
                        bean.setCreateTime("");
                        bean.setUpdateTime("");
                        siteMapper.insertSelective(bean);
                    }


                }
            }





        }


    }


   /* @Test

    public void httpGet(final String url, final Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(url).build(); //添加头部信息
                okHttpClient.newCall(request).enqueue(callback);
            }
        }).start();
    }*/

    @Test
    public void getOne() {
        OkHttpClient okHttpClient = new OkHttpClient();
        OkHttpClient build = okHttpClient.newBuilder().build();
        System.out.println(visitorMapper.selectByPrimaryKey(1));
    }



    @Test
    public void list() {
        System.out.println(visitorMapper.getList(null));
    }

    @Test
    @Transactional
    public void delete() {
        System.out.println(visitorMapper.deleteByPrimaryKey(1));
    }

    @Test
    //@Transactional
    public void update() {
        Visitor visitor = visitorMapper.selectByPrimaryKey(1);
        visitor.setOs("windows");
        int i = visitorMapper.updateByPrimaryKeySelective(visitor);
    }

    @Test
    public void insert() {
        Visitor visitor = new Visitor();
        visitor.setId(0);
        visitor.setIp("47.94.153.206");
        visitor.setOs("linux");
        visitor.setBrowser("QQ浏览器");
        visitor.setAddress("广州");
        visitor.setCreate_time(new Date());
        int i = visitorMapper.insertSelective(visitor);


    }

    @Test
    @Transactional
    public void deleteBatch() {
        Integer[] ids = {1, 2, 3};
        visitorMapper.deleteBatch(ids);
    }

    @Test
    @Transactional
    public void saveBatch() {
        ArrayList<Visitor> visitors = Lists.newArrayList();
        Visitor visitor = new Visitor();
        visitor.setIp("1");
        visitor.setOs("1");
        visitor.setBrowser("1");
        visitor.setAddress("1");
        visitor.setCreate_time(new Date());
        Visitor visitor2 = new Visitor();
        visitor2.setIp("2");
        visitor2.setOs("2");
        visitor2.setBrowser("2");
        visitor2.setAddress("2");
        visitor2.setCreate_time(new Date());
        visitors.add(visitor);
        visitors.add(visitor2);
        System.out.println(visitorMapper.batchInsert(visitors));


    }

    @Test
    public void batchSelect() {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3);
        List<Visitor> visitors = visitorMapper.batchSelect(integers);
        visitors.get(0).setOs("linux");
        visitors.get(1).setOs("");

        visitors.forEach((e) -> e.setBrowser("firefox"));
        System.out.println(visitorMapper.batchUpdate(visitors));
    }


}
