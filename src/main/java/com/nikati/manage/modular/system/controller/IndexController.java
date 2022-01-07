/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nikati.manage.modular.system.controller;

import java.util.Date;

import cn.stylefeng.roses.core.base.controller.BaseController;

import com.nikati.manage.core.util.AddressUtils;
import com.nikati.manage.core.util.IpUtils;
import com.nikati.manage.modular.system.dao.VisitorMapper;
import com.nikati.manage.modular.system.model.Visitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nikati.manage.core.common.node.MenuNode;
import com.nikati.manage.modular.system.model.Category;
import com.nikati.manage.modular.system.service.IOperationLogService;
import com.nikati.manage.modular.system.service.impl.CategoryServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author jsnjfz
 * @Date 2019/7/25 21:23 首页控制器
 */
@Controller
@CacheConfig(cacheNames = "index")
@Slf4j
public class IndexController extends BaseController {
    // 1 标题
    public static final String CACHE_INDEX_TITLES = "index_titles";
    // 2 分类
    public static final String CACHE_CATEGORY = "index_categorys";

    @Autowired
    private VisitorMapper visitorMapper;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private RedisTemplate redisTemplate;

    private void countUserDetailMessage(HttpServletRequest request) {
        int serverPort = request.getServerPort();
        String ipAddr = IpUtils.getIpAddr(request);
        List<String> osAndBrowserInfo = IpUtils.getOsAndBrowserInfo(request);
        String osName = osAndBrowserInfo.get(0);
        String bronsor = osAndBrowserInfo.get(1);
        ipAddr = "222.76.8.158";
        // todo 地址不可用目前
        //String queryAddress = AddressUtils.queryAddress(ipAddr);
        System.out.println("");
        Visitor visitor = new Visitor();
        visitor.setIp(ipAddr);
        visitor.setOs(osName);
        visitor.setBrowser(bronsor);
        // visitor.setAddress(queryAddress);
        visitor.setAddress("上海");
        visitor.setCreate_time(new Date());
        visitorMapper.insertSelective(visitor);
    }

    /**
     * 1 跳转到首页
     */
    @RequestMapping("/")
    public String index(Model model, HttpServletRequest httpServletRequest) {
        countUserDetailMessage(httpServletRequest);
        List<MenuNode> titles = null;
        List<Category> categorySiteList = null;
        titles = redisTemplate.opsForList().range(CACHE_CATEGORY, 0, -1);
        categorySiteList = redisTemplate.opsForList().range(CACHE_INDEX_TITLES, 0, -1);
        if (CollectionUtils.isEmpty(titles) || CollectionUtils.isEmpty(categorySiteList)) {
            List<MenuNode> menus = categoryService.getCatogryNode(new HashMap<>());

            titles = MenuNode.buildTitle(menus);
            redisTemplate.opsForList().rightPushAll(CACHE_CATEGORY, titles);
            // 处理分类目录
            categorySiteList = categoryService.getCatogrySite(null);
            redisTemplate.opsForList().rightPushAll(CACHE_INDEX_TITLES, categorySiteList);
        }
        model.addAttribute("categorySiteList", categorySiteList);
        model.addAttribute("titles", titles);
        return "/index.html";
    }


    @RequestMapping("/search/{wd}")
    public String s(Model model, @PathVariable(value = "wd") String wd) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("title", wd);
        List<MenuNode> menus = categoryService.getCatogryNode(map);
        List<MenuNode> titles = MenuNode.buildTitle(menus);
        List<Category> categorySiteList = categoryService.getCatogrySiteByinfo(map);
        List<Category> resultList = new ArrayList<Category>();
        for (Category category : categorySiteList) {
            if (null != category.getSites() && category.getSites().size() != 0) {
                resultList.add(category);
            }
        }
        model.addAttribute("categorySiteList", resultList);
        model.addAttribute("titles", titles);
        System.out.println(titles);
        return "/index.html";
    }

    /**
     * 跳转到关于页面
     */
    @RequestMapping("/about")
    public String about(Model model) {
        return "/about.html";
    }

}
