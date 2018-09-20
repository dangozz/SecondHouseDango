package com.yunji.dango.secondhouse.dao;

import com.yunji.dango.secondhouse.model.SecondHouseDetail;
import com.yunji.dango.shiro.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecondHouseDetailDao extends BaseDao<SecondHouseDetail>{

    List<Integer> findParentIdByPhone(@Param("phone")String phone);

}
