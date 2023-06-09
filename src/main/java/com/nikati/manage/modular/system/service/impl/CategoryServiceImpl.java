package com.nikati.manage.modular.system.service.impl;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.nikati.manage.core.common.node.MenuNode;
import com.nikati.manage.core.common.node.ZTreeNode;
import com.nikati.manage.core.util.Pager;
import com.nikati.manage.modular.system.dao.CategoryMapper;
import com.nikati.manage.modular.system.dao.SiteMapper;
import com.nikati.manage.modular.system.model.Category;
import com.nikati.manage.modular.system.model.Site;
import com.nikati.manage.modular.system.service.BaseService;

import java.util.*;

/**
 * @Author jsnjfz
 * @Date 2019/7/21 15:17
 * 分类相关业务接口实现类
 */
@Service
//@CacheConfig(cacheNames = "categories")
public class CategoryServiceImpl extends BaseService<Category> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private SiteMapper siteMapper;

    public static Pager<Category> pager = null;

    public  List<Category> getListByParentId(Integer id){
        return categoryMapper.getListByParentId(id);
    }

//    @Cacheable(key = "'categories-many-'+ #p0")
    public List<Category> getCatogry(Map<String, Object> map) {
        return categoryMapper.getCatogry(map);
    }
//    @Cacheable(key = "'categories-many-'+ #p0")
    public List<MenuNode> getCatogryNode(Map<String, Object> map) {
        return categoryMapper.getCatogryNode(map);
    }
//    @Cacheable(key = "'categories-all'")
    public List<ZTreeNode> tree() {
        return categoryMapper.tree();
    }

//    @Cacheable(key = "'categories-many-'+ #p0")
    public List<Category> getCatogrySite(Map<String, Object> map) {
        List<Category> categoryList = categoryMapper.getList(map);
        List<Site> siteList = siteMapper.getList(map);
        for (Category category : categoryList) {
            List<Site> sites = new ArrayList<>();
            for (Site site : siteList) {
                if (Objects.equals(site.getCategoryId(),  category.getId()) ) {
                    sites.add(site);
                }
            }
            category.setSites(sites);
        }
        return categoryList;
    }

    /**
     * 站内查询
     *
     * @param map
     * @return
     */
//    @Cacheable(key = "'categories-many-'+ #p0")
    public List<Category> getCatogrySiteByinfo(Map<String, Object> map) {
        List<Category> categoryList = categoryMapper.getList(map);
        List<Category> resultList = new ArrayList<>();
        List<Site> siteList = siteMapper.getList(map);
        if (siteList.size() == 0) {
            return categoryList;
        }
        for (Category category : categoryList) {
            List<Site> sites = new ArrayList<>();
            for (Site site : siteList) {
                if (site.getCategoryId() .equals( category.getId())) {
                    sites.add(site);
                }
            }
            if (sites.size() != 0) {
                category.setSites(sites);
            }
            if (null == category.getSites() || category.getSites().size() == 0) {
            } else {
                resultList.add(category);
            }
        }
        return resultList;
    }


}
