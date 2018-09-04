package com.yunji.dango.shiro.service;

import java.util.List;
import java.util.Map;

public interface BaseService<T>{
    void setBaseDao();

    List<T> findAllModel();

    List<T> findModelByCondition(Map<String, String> paramMap);

    T findOneModel(Map<String, String> paramMap);

    T findModelById(Integer paramInteger);

    int insertModel(T paramT);

    int deleteModel(T paramT);

    int updateModel(T paramT);

    int findTotal();
}
