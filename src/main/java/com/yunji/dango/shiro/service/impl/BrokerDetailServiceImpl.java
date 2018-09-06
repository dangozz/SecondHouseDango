package com.yunji.dango.shiro.service.impl;

import com.yunji.dango.shiro.dao.BrokerDetailDao;
import com.yunji.dango.shiro.model.BrokerDetail;
import com.yunji.dango.shiro.service.BrokerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: DANGO
 * @date 2018/9/6 11:13
 * @Description:
 */
@Service
public class BrokerDetailServiceImpl extends BaseServiceImpl<BrokerDetail> implements BrokerDetailService {

    @Autowired
    private BrokerDetailDao brokerDetailDao;

    @Override
    public void setBaseDao() {
        super.setBaseDao(brokerDetailDao);
    }

    @Override
    public BrokerDetail findOneModel(Map<String, String> map) {
        return brokerDetailDao.findOneModel(map);
    }

    @Override
    public int insertModel(BrokerDetail brokerDetail) {
        return brokerDetailDao.insertModel(brokerDetail);
    }

    @Override
    public BrokerDetail findModelById(Integer id) {
        return brokerDetailDao.findModelById(id);
    }

    @Override
    public List<BrokerDetail> findAllModel() {
        return brokerDetailDao.findAllModel();
    }
}
