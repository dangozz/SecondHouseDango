package com.yunji.dango.shiro.service.impl;

import com.yunji.dango.shiro.dao.BaseDao;
import com.yunji.dango.shiro.service.BaseService;
import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl<T> implements BaseService<T>{
    private BaseDao<T> baseDao;

    public void setBaseDao(BaseDao<T> baseDao){
        this.baseDao = baseDao;
    }

    public List<T> findAllModel(){
        return baseDao.findAllModel();
    }

    public List<T> findModelByCondition(Map<String, String> map){
        return baseDao.findModelByCondition(map);
    }

    public T findOneModel(Map<String, String> map){
        return baseDao.findOneModel(map);
    }

    public T findModelById(Integer id){
        return baseDao.findModelById(id);
    }

    public int insertModel(T t){
        return baseDao.insertModel(t);
    }

    public int deleteModel(T t){
        return baseDao.deleteModel(t);
    }

    public int updateModel(T t){
        return baseDao.updateModel(t);
    }

    public int findTotal(){
        return baseDao.findTotal();
    }
}
