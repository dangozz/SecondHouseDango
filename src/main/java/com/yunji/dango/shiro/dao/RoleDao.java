package com.yunji.dango.shiro.dao;

import com.yunji.dango.shiro.model.Role;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleDao extends BaseDao<Role>{
    List<Role> findAdminRole(@Param("id") Integer paramInteger);

    Integer deleteAdminRole(@Param("id") Integer paramInteger);

    Integer updateRolePermisson(List paramList);

    Integer deleteRolePermissionByRoleId(@Param("role_id") Integer paramInteger);
}
