package com.yunji.dango.shiro.service;

import java.util.List;
import java.util.Map;

public interface BaseService<T>{
    void setBaseDao();

    List<T> findAllModel();

    List<T> findModelByCondition(Map<String, String> paramMap);

    T findOneModel(Map<String, String> paramMap);

    T findModelById(Integer id);

    int insertModel(T t);

    int deleteModel(T t);

    int updateModel(T t);

    int findTotal();
}
