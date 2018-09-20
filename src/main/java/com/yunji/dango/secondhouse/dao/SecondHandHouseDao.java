package com.yunji.dango.secondhouse.dao;

import com.yunji.dango.secondhouse.model.SecondHandHouse;
import com.yunji.dango.shiro.dao.BaseDao;

import java.util.Map;

public interface SecondHandHouseDao extends BaseDao<SecondHandHouse>{

    Integer updateExamine(Map<String,String> map);

}
