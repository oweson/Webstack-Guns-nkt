package com.nikati.manage.modular.system.dao;

import com.nikati.manage.modular.system.model.Visitor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VisitorMapper extends BaseDao<Visitor> {

    int  deleteBatch(Integer[] ids);
    int batchInsert(List<Visitor> visitorList);

    int batchUpdate(List<Visitor> visitors);

    List<Visitor> batchSelect(@Param("ids") List<Integer> ids);

}
