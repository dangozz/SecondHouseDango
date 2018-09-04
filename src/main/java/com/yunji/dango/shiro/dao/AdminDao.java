package com.yunji.dango.shiro.dao;

import com.yunji.dango.shiro.model.Admin;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminDao extends BaseDao<Admin>{
    Integer updateAdminRole(List paramList);

    Integer deleteAdminRoleByAdminId(@Param("admin_id") Integer paramInteger);
}
