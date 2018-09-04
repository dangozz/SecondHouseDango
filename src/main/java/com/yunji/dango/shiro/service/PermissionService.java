package com.yunji.dango.shiro.service;

import com.yunji.dango.shiro.model.Permission;
import java.util.List;

public interface PermissionService extends BaseService<Permission>{
    List<Permission> findRolePermission(Integer paramInteger);

    Integer deleteRolePermission(Integer paramInteger);
}
