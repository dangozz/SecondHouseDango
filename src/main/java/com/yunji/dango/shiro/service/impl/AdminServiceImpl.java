package com.yunji.dango.shiro.service.impl;

import com.yunji.dango.shiro.dao.AdminDao;
import com.yunji.dango.shiro.model.Admin;
import com.yunji.dango.shiro.service.AdminService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService{
    @Autowired
    private AdminDao adminDao;

    @Override
    public void setBaseDao(){
        super.setBaseDao(adminDao);
    }

    @Override
    public int findTotal(){
        return adminDao.findTotal();
    }

    @Override
    @Cacheable(value={"dango_5"}, key="'admin'")
    public List<Admin> findAllModel(){
        return adminDao.findAllModel();
    }

    @Override
    @CacheEvict(value={"dango_5"}, key="'admin'", beforeInvocation=true)
    public int insertModel(Admin admin){
        return adminDao.insertModel(admin);
    }

    @Override
    @CacheEvict(value={"dango_5"}, key="'admin'", beforeInvocation=true)
    public int deleteModel(Admin admin){
        return adminDao.deleteModel(admin);
    }

    @Override
    @Caching(evict={
            @CacheEvict(value={"dango_5"}, key="'admin_'+#admin.getId()", beforeInvocation=true),
            @CacheEvict(value={"dango_5"}, key="'admin'", beforeInvocation=true)
    })
    public int updateModel(Admin admin){
        return adminDao.updateModel(admin);
    }

    @Override
    @Cacheable(value={"dango_5"}, key="'admin_'+#map.get('id')")
    public Admin findOneModel(Map<String, String> map){
        return (Admin)adminDao.findOneModel(map);
    }

    @Override
    @CacheEvict(value={"dango_6"}, key="'admin_role_'+#list.get(0).get('admin_id')")
    public Integer updateAdminRole(List list){
        return adminDao.updateAdminRole(list);
    }

    @Override
    @CacheEvict(value={"dango_6"}, key="'admin_role_'+#id")
    public Integer deleteAdminRoleByAdminId(Integer id){
        return adminDao.deleteAdminRoleByAdminId(id);
    }
}
