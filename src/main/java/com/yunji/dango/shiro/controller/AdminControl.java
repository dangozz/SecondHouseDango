package com.yunji.dango.shiro.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunji.dango.shiro.model.Admin;
import com.yunji.dango.shiro.model.Permission;
import com.yunji.dango.shiro.model.Role;
import com.yunji.dango.shiro.service.AdminService;
import com.yunji.dango.shiro.service.PermissionService;
import com.yunji.dango.shiro.service.RoleService;
import com.yunji.dango.shiro.uti.MD5;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yunji.dango.shiro.uti.MapUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/shiro"})
public class AdminControl{
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping({"/findAllAdmin.do"})
    @ResponseBody
    public Map findAllAdmin(@RequestBody String json){
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        Integer page = Integer.parseInt(jsonMap.get("page"));
        Integer num = Integer.parseInt(jsonMap.get("num"));

        Page page_ = PageHelper.startPage(page, num, true);
        List<Admin> list = adminService.findAllModel();

        List<Admin> resultList = new ArrayList<>();
        resultList.addAll(list);

        Map<String, Object> map = new HashMap<>();
        map.put("admins", resultList);
        map.put("count", adminService.findTotal());

        return map;
    }

    @RequestMapping({"/findOneAdmin.do"})
    @ResponseBody
    public Map findOneAdmin(@RequestBody String json){
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        String id = jsonMap.get("id");

        Map<String, Object> map = new HashMap<>();
        map.put("admin", adminService.findOneModel(jsonMap));
        map.put("role", roleService.findAdminRole(Integer.parseInt(id)));
        return map;
    }

    @RequestMapping({"/addAdmin.do"})
    @ResponseBody
    public Map addAdmin(@RequestBody String json){
        Admin admin = getAdmin(json);
        admin.setPassword(MD5.MD5Encode(admin.getPassword()));
        int i = adminService.insertModel(admin);
        return MapUtil.getReturnMapByNum(i,"新增");
    }

    @RequestMapping({"/delAdmin.do"})
    @ResponseBody
    public Map delAdmin(@RequestBody String json){
        Admin admin = getAdmin(json);
        adminService.deleteAdminRoleByAdminId(admin.getId());
        int i = adminService.deleteModel(admin);
        return MapUtil.getReturnMapByNum(i,"删除");
    }

    @RequestMapping({"/updateAdmin.do"})
    @ResponseBody
    public Map updateAdmin(@RequestBody String json){
        Admin admin = getAdmin(json);
        if ((admin.getPassword() != null) && (!"".equals(admin.getPassword()))) {
            admin.setPassword(MD5.MD5Encode(admin.getPassword()));
        }
        int i = adminService.updateModel(admin);
        return MapUtil.getReturnMapByNum(i,"修改");
    }

    @RequestMapping({"/updateAdminRole.do"})
    @ResponseBody
    public Map updateAdminRole(@RequestBody String json){
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        Integer id = Integer.parseInt(jsonMap.get("id"));

        String[] roles = (jsonMap.get("roles")).split(",");

        List<Map<String, Integer>> values = new ArrayList<>();
        for (String role : roles){
            Map<String, Integer> value = new HashMap<>();
            value.put("admin_id", id);
            value.put("role_id", Integer.parseInt(role));
            values.add(value);
        }
        adminService.deleteAdminRoleByAdminId(id);
        int i = adminService.updateAdminRole(values);
        return MapUtil.getReturnMapByNum(i,"修改");
    }

    @RequestMapping({"/findAdminPermission.do"})
    @ResponseBody
    public List findAdminPermission(@RequestBody String json){
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        Integer id = Integer.parseInt((jsonMap.get("id")).trim());

        List<Role> roles = roleService.findAdminRole(id);
        List<Permission> permissions = new ArrayList<>();
        for (Role role : roles) {
            permissions.addAll(permissionService.findRolePermission(role.getId()));
        }
        return permissions;
    }

    private Admin getAdmin(String json){
        Admin admin = new Admin();

        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        String id = jsonMap.get("id");
        String password = jsonMap.get("password");
        String name = jsonMap.get("name");
        String phone = jsonMap.get("phone");
        String position = jsonMap.get("position");
        String department = jsonMap.get("department");
        if ((id != null) && (!"".equals(id))) {
            admin.setId(Integer.parseInt(id));
        }
        admin.setName(name);
        admin.setPassword(password);
        admin.setPhone(phone);
        admin.setPosition(position);
        admin.setDepartment(department);

        return admin;
    }
}
