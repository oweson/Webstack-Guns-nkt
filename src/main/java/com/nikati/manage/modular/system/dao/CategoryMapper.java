package com.nikati.manage.modular.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nikati.manage.core.common.node.MenuNode;
import com.nikati.manage.core.common.node.ZTreeNode;
import com.nikati.manage.modular.system.model.Category;
import org.apache.ibatis.annotations.Param;

public interface CategoryMapper extends BaseDao<Category> {
    /**
     * 1 查询分类
     */

    List<Category> getCatogry(Map map);

    List<MenuNode> getCatogryNode(Map map);

    /**
     * 3 获取ztree的节点列表
     */
    List<ZTreeNode> tree();

    List<Category> getListByParentId(@Param("id") Integer id);
}