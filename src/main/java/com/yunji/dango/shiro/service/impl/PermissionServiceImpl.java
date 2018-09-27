package com.yunji.dango.shiro.service.impl;

import com.yunji.dango.shiro.dao.PermissionDao;
import com.yunji.dango.shiro.model.Permission;
import com.yunji.dango.shiro.service.PermissionService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService{
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public void setBaseDao(){
        super.setBaseDao(permissionDao);
    }

    @Override
    public int findTotal(){
        return permissionDao.findTotal();
    }

    @Override
//    @Cacheable(value={"dango_5"}, key="'permission'")
    public List<Permission> findAllModel(){
        return permissionDao.findAllModel();
    }

    @Override
    @CacheEvict(value={"dango_5"}, key="'permission'", beforeInvocation=true)
    public int insertModel(Permission permission){
        return permissionDao.insertModel(permission);
    }

    @Override
    @CacheEvict(value={"dango_5"}, key="'permission'", beforeInvocation=true)
    public int deleteModel(Permission permission){
        return permissionDao.deleteModel(permission);
    }

    @Override
    @Caching(evict={
            @CacheEvict(value={"dango_5"}, key="'permission_'+#permission.getId()", beforeInvocation=true),
            @CacheEvict(value={"dango_5"}, key="'permission'", beforeInvocation=true),
            @CacheEvict(value={"dango_6"}, allEntries=true)
    })
    public int updateModel(Permission permission){
        return permissionDao.updateModel(permission);
    }

    @Override
    @Cacheable(value={"dango_5"}, key="'permission_'+#map.get('id')")
    public Permission findOneModel(Map<String, String> map){
        return (Permission)permissionDao.findOneModel(map);
    }

    @Override
    @Cacheable(value={"dango_6"}, key="'role_permission_'+#id")
    public List<Permission> findRolePermission(Integer id){
        return permissionDao.findRolePermission(id);
    }

    @Override
    @CacheEvict(value={"dango_6"}, allEntries=true)
    public Integer deleteRolePermission(Integer id){
        return permissionDao.deleteRolePermission(id);
    }
}
