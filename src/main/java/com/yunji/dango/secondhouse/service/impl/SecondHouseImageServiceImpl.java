package com.yunji.dango.secondhouse.service.impl;

import com.yunji.dango.secondhouse.dao.SecondHouseImageDao;
import com.yunji.dango.secondhouse.model.SecondHouseImage;
import com.yunji.dango.secondhouse.service.SecondHouseImageService;
import com.yunji.dango.shiro.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecondHouseImageServiceImpl extends BaseServiceImpl<SecondHouseImage> implements SecondHouseImageService{
    @Autowired
    private SecondHouseImageDao secondHouseImageDao;

    @Override
    public void setBaseDao(){
        setBaseDao(secondHouseImageDao);
    }

    @Override
    public SecondHouseImage findModelById(Integer id){
        return secondHouseImageDao.findModelById(id);
    }
}
