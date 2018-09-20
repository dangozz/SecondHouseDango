package com.yunji.dango.shiro.service;

import com.yunji.dango.shiro.model.BrokerDetail;

import java.util.Map;

/**
 * @author: DANGO
 * @date 2018/9/6 11:13
 * @Description:
 */
public interface BrokerDetailService extends BaseService<BrokerDetail> {

    Integer updateExamine(Map<String,String> map);
}
