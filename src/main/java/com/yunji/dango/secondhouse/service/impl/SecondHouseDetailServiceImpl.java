package com.yunji.dango.secondhouse.service.impl;

import com.yunji.dango.secondhouse.dao.SecondHouseDetailDao;
import com.yunji.dango.secondhouse.model.SecondHouseDetail;
import com.yunji.dango.secondhouse.service.SecondHouseDetailService;
import com.yunji.dango.shiro.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecondHouseDetailServiceImpl extends BaseServiceImpl<SecondHouseDetail> implements SecondHouseDetailService{
    @Autowired
    private SecondHouseDetailDao secondHouseDetailDao;

    @Override
    public void setBaseDao(){
        super.setBaseDao(secondHouseDetailDao);
    }

    @Override
    public SecondHouseDetail findModelById(Integer id){
        return secondHouseDetailDao.findModelById(id);
    }

    @Override
    public List<Integer> findParentIdByPhone(String phone) {
        return secondHouseDetailDao.findParentIdByPhone(phone);
    }
}
