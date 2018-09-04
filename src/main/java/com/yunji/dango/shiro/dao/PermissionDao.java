package com.yunji.dango.shiro.dao;

import com.yunji.dango.shiro.model.Permission;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionDao extends BaseDao<Permission>{
    List<Permission> findRolePermission(@Param("id") Integer paramInteger);

    Integer deleteRolePermission(@Param("id") Integer paramInteger);
}
