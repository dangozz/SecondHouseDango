package com.yunji.dango.shiro.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface BaseDao<T>{
    List<T> findAllModel();

    List<T> findModelByCondition(Map<String, String> paramMap);

    T findOneModel(Map<String, String> paramMap);

    T findModelById(@Param("id") Integer id);

    int insertModel(@Param("t") T t);

    int deleteModel(@Param("t") T t);

    int updateModel(@Param("t") T t);

    int findTotal();
}
