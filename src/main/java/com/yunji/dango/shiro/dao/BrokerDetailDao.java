package com.yunji.dango.shiro.dao;

import com.yunji.dango.shiro.model.BrokerDetail;

import java.util.Map;

/**
 * @author: DANGO
 * @date 2018/9/6 11:12
 * @Description:
 */
public interface BrokerDetailDao extends BaseDao<BrokerDetail> {

    Integer updateExamine(Map<String,String> map);
}
