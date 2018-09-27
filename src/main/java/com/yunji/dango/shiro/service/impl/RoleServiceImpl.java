package com.yunji.dango.shiro.service.impl;

import com.yunji.dango.shiro.dao.RoleDao;
import com.yunji.dango.shiro.model.Role;
import com.yunji.dango.shiro.service.RoleService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{
    @Autowired
    private RoleDao roleDao;

    @Override
    public void setBaseDao(){
        super.setBaseDao(roleDao);
    }

    @Override
    public int findTotal(){
        return roleDao.findTotal();
    }

    @Override
//    @Cacheable(cacheNames={"dango_5"}, key="'role'")
    public List<Role> findAllModel(){
        return roleDao.findAllModel();
    }

    @Override
    @CacheEvict(value={"dango_5"}, key="'role'")
    public int insertModel(Role role){
        return roleDao.insertModel(role);
    }

    @Override
    @CacheEvict(value={"dango_5"}, key="'role'")
    public int deleteModel(Role role){
        return roleDao.deleteModel(role);
    }

    @Override
    @Caching(evict={
            @CacheEvict(value={"dango_5"}, key="'role'"),
            @CacheEvict(value={"dango_5"}, key="'role_'+#role.getId()"),
            @CacheEvict(value={"dango_6"}, allEntries=true)
    })
    public int updateModel(Role role){
        return roleDao.updateModel(role);
    }

    @Override
    @Cacheable(cacheNames={"dango_5"}, key="'role_'+#map.get('id')")
    public Role findOneModel(Map<String, String> map){
        return (Role)roleDao.findOneModel(map);
    }

    @Override
    @Cacheable(value={"dango_6"}, key="'admin_role_'+#id")
    public List<Role> findAdminRole(Integer id){
        return roleDao.findAdminRole(id);
    }

    @Override
    @CacheEvict(value={"dango_6"}, allEntries=true)
    public Integer deleteAdminRole(Integer id){
        return roleDao.deleteAdminRole(id);
    }

    @Override
    @CacheEvict(value={"dango_6"}, key="'role_permission_'+#list.get(0).get('role_id')")
    public Integer updateRolePermisson(List list){
        return roleDao.updateRolePermisson(list);
    }

    @Override
    @CacheEvict(value={"dango_6"}, key="'role_permission_'+#id")
    public Integer deleteRolePermissionByRoleId(Integer id){
        return roleDao.deleteRolePermissionByRoleId(id);
    }
}
