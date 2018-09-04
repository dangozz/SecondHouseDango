package com.yunji.dango.secondhouse.service.impl;

import com.yunji.dango.secondhouse.dao.SecondHandHouseDao;
import com.yunji.dango.secondhouse.model.SecondHandHouse;
import com.yunji.dango.secondhouse.service.SecondHandHouseService;
import com.yunji.dango.shiro.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecondHandHouseServiceImpl extends BaseServiceImpl<SecondHandHouse> implements SecondHandHouseService{
    @Autowired
    private SecondHandHouseDao secondHandHouseDao;

    @Override
    public void setBaseDao(){
        super.setBaseDao(secondHandHouseDao);
    }

    @Override
    public SecondHandHouse findModelById(Integer id){
        return (SecondHandHouse)secondHandHouseDao.findModelById(id);
    }
}
