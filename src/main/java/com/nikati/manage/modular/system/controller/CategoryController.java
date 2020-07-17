package com.nikati.manage.modular.system.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;

import com.nikati.manage.modular.system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nikati.manage.core.common.exception.BizExceptionEnum;
import com.nikati.manage.core.common.node.ZTreeNode;
import com.nikati.manage.modular.system.model.Category;
import com.nikati.manage.modular.system.service.impl.CategoryServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author fz
 * @Date 2019/7/21 14:29
 * 分类控制器
 */
@Controller
@RequestMapping("category")
public class CategoryController extends BaseController {
    // 1 标题
    public static final String CACHE_INDEX_TITLES = "index_titles";
    // 2 分类
    public static final String CACHE_CATEGORY = "index_categorys";

    private static String PREFIX = "/system/site/";
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CategoryServiceImpl categoryService;

    /**
     * 0 跳转到菜单列表列表页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "category.html";
    }


    /**
     * 1 获取分类列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String title) {
        Map map = new HashMap();
        map.put("title", title);
        List<Map<String, Object>> mapList = categoryService.getCatogry(map);
        return super.warpObject(new BaseControllerWrapper(mapList) {
            @Override
            protected void wrapTheMap(Map<String, Object> map) {
            }
        });
    }

    /**
     * 2 跳转到添加分类
     */
    @RequestMapping("/category_add")
    public String categoryAdd() {
        return PREFIX + "category_add.html";
    }

    /**
     * 3 跳转到修改分类
     * ok!
     */
    @RequestMapping("/category_update/{id}")
    public String categoryUpdate(@PathVariable Integer id, Model model) {
        cacheSame();
        Category category = categoryService.get(id);
        model.addAttribute(category);
        int pId = category.getParentId();
        if (pId == 0) {
            model.addAttribute("pName", "顶级");
            return PREFIX + "category_edit.html";
        }
        model.addAttribute("pName", categoryService.get(pId).getTitle());
        return PREFIX + "category_edit.html";
    }

    /**
     * 4 获取分类的tree列表
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = categoryService.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }

    /**
     * 5 新增分类
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Category category) {
        cacheSame();
        int level = category.getParentId() == 0 ? 0 : categoryService.get(category.getParentId()).getLevels();
        // level区分一级和二级分类
        category.setLevels(level + 1);
        categoryService.saveOrUpdate(category, "");
        return SUCCESS_TIP;
    }


    /**
     * 6 修改分类
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Category category) {
        cacheSame();
        if (ToolUtil.isEmpty(category) || category.getId() == null) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        int level = category.getParentId() == 0 ? 0 : categoryService.get(category.getParentId()).getLevels();
        // 无限极的分类
        category.setLevels(level + 1);
        categoryService.saveOrUpdate(category, category.getId());
        return SUCCESS_TIP;
    }


    /**
     * 7 删除分类
     * todo 问题修复
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer id) {
        cacheSame();
        int size = categoryService.getListByParentId(id).size();
        if(size>0){
            System.out.println(1/0);
            return "";
            //return "下级有数据，禁止删除！";
        }
        categoryService.delete(id);
        return SUCCESS_TIP;
    }


    public void cacheSame() {
        redisTemplate.expire(CACHE_CATEGORY, 0, TimeUnit.SECONDS);
        redisTemplate.expire(CACHE_INDEX_TITLES, 0, TimeUnit.SECONDS);

    }

}
