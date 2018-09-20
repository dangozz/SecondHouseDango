package com.yunji.dango.secondhouse.service;

import com.yunji.dango.secondhouse.model.SecondHandHouse;
import com.yunji.dango.shiro.service.BaseService;

import java.util.Map;

public interface SecondHandHouseService extends BaseService<SecondHandHouse>{

    Integer updateExamine(Map<String,String> map);

}
