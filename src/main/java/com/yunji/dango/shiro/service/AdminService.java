package com.yunji.dango.shiro.service;

import com.yunji.dango.shiro.model.Admin;
import java.util.List;

public interface AdminService extends BaseService<Admin>{
    Integer updateAdminRole(List paramList);

    Integer deleteAdminRoleByAdminId(Integer id);

    List<Admin> findAdminByNameOrPhone(String nameOrPhone);
}
