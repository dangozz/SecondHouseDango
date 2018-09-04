package com.yunji.dango.shiro.service;

import com.yunji.dango.shiro.model.Role;
import java.util.List;

public interface RoleService extends BaseService<Role>{
    List<Role> findAdminRole(Integer paramInteger);

    Integer deleteAdminRole(Integer paramInteger);

    Integer updateRolePermisson(List paramList);

    Integer deleteRolePermissionByRoleId(Integer paramInteger);
}
