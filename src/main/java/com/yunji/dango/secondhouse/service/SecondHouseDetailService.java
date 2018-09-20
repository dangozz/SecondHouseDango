package com.yunji.dango.secondhouse.service;

import com.yunji.dango.secondhouse.model.SecondHouseDetail;
import com.yunji.dango.shiro.service.BaseService;

import java.util.List;

public interface SecondHouseDetailService extends BaseService<SecondHouseDetail>{
    List<Integer> findParentIdByPhone(String phone);
}
