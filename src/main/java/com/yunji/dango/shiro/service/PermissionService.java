package com.yunji.dango.shiro.service;

import com.yunji.dango.shiro.model.Permission;
import java.util.List;

public interface PermissionService extends BaseService<Permission>{
    List<Permission> findRolePermission(Integer id);

    Integer deleteRolePermission(Integer id);
}
