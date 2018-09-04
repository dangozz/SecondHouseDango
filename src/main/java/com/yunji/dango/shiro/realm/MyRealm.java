package com.yunji.dango.shiro.realm;

import com.yunji.dango.redis.RedisCache;
import com.yunji.dango.shiro.model.Admin;
import com.yunji.dango.shiro.model.Permission;
import com.yunji.dango.shiro.model.Role;
import com.yunji.dango.shiro.service.AdminService;
import com.yunji.dango.shiro.service.PermissionService;
import com.yunji.dango.shiro.service.RoleService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm{
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RedisCache redisCache;
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
        String username = (String)principalCollection.getPrimaryPrincipal();
        Admin admin = null;
        if (redisCache.getObject("user" + username) != null){
            admin = (Admin)redisCache.getObject("user" + username);
        }else{
            Map<String, String> map = new HashMap<>();
            map.put("name", username);
            admin = adminService.findOneModel(map);
            redisCache.putObject("user" + username, admin, 0L);
        }
        if (admin != null){
            if (redisCache.getObject("permission" + admin.getId()) != null) {
                return (AuthorizationInfo)redisCache.getObject("permission" + admin.getId());
            }
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<Role> roles = roleService.findAdminRole(admin.getId());
            List<Permission> permissions = new ArrayList<>();
            for (Role role : roles)
            {
                info.addRole(role.getRoleName());
                permissions.addAll(permissionService.findRolePermission(role.getId()));
            }
            redisCache.putObject("permission" + admin.getId(), info, 0L);
        }
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException
    {
        String username = (String)authenticationToken.getPrincipal();
        Map<String, String> map = new HashMap<>();
        map.put("name", username);


        Admin admin = adminService.findOneModel(map);
        if (admin != null){
            logger.info("用户登录---" + admin);
            redisCache.putObject("test" + admin.getId(), "test" + admin.getPassword(), 0L);
            logger.info("--------------" + redisCache.getObject(new StringBuilder().append("test").append(admin.getId()).toString()));
            return new SimpleAuthenticationInfo(admin, admin.getPassword(), null, getName());
        }
        return null;
    }
}
