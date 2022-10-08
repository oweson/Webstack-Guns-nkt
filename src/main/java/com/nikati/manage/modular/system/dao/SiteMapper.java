package com.nikati.manage.modular.system.dao;

import com.nikati.manage.modular.system.model.Site;

import java.util.List;

public interface SiteMapper extends BaseDao<Site> {

    void batchUpdate(List<Integer> ids);


    int delete(Integer id);

}
