package com.yunji.dango.shiro.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunji.dango.shiro.model.Role;
import com.yunji.dango.shiro.service.PermissionService;
import com.yunji.dango.shiro.service.RoleService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yunji.dango.shiro.uti.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/shiro"})
public class RoleControl{
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping({"/findAllRole.do"})
    @ResponseBody
    public Map findAllRole(@RequestBody String json){
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        Integer page = Integer.parseInt(jsonMap.get("page"));
        Integer num = Integer.parseInt(jsonMap.get("num"));
        Page page_ = PageHelper.startPage(page.intValue(), num.intValue(), true);

        List<Role> list = roleService.findAllModel();

        List<Role> resultList = new ArrayList<>();
        resultList.addAll(list);

        Map<String, Object> map = new HashMap<>();
        map.put("admins", resultList);
        map.put("count", roleService.findTotal());

        return map;
    }

    @RequestMapping({"/findOneRole.do"})
    @ResponseBody
    public Map findOneRole(@RequestBody String json){
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        String id = jsonMap.get("id");

        Map<String, Object> map = new HashMap<>();
        map.put("role", roleService.findOneModel(jsonMap));
        map.put("permissions", permissionService.findRolePermission(Integer.parseInt(id)));
        return map;
    }

    @RequestMapping({"/addRole.do"})
    @ResponseBody
    public Map addRole(@RequestBody String json){
        Role role = getRole(json);
        int i = roleService.insertModel(role);
        return MapUtil.getReturnMapByNum(i,"新增");
    }

    @RequestMapping({"/delRole.do"})
    @ResponseBody
    public Map delRole(@RequestBody String json){
        Role role = getRole(json);

        int n = roleService.deleteAdminRole(role.getId());
        roleService.deleteRolePermissionByRoleId(role.getId());
        int i = roleService.deleteModel(role);
        Map<String, Object> map = new HashMap<>();
        if (i == 1){
            map.put("message", "删除成功");
            map.put("status", 200);
            map.put("num", n);
        }
        else{
            map.put("status", 400);
            map.put("message", "删除失败");
        }
        return map;
    }

    @RequestMapping({"/updateRole.do"})
    @ResponseBody
    public Map updateRole(@RequestBody String json){
        Role role = getRole(json);
        int i = roleService.updateModel(role);
        return MapUtil.getReturnMapByNum(i,"修改");
    }

    @RequestMapping({"/updateRolePermisson.do"})
    @ResponseBody
    public Map updateRolePermisson(@RequestBody String json){
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        Integer id = Integer.parseInt(jsonMap.get("id"));

        String[] permissions = (jsonMap.get("permissions")).split(",");

        List<Map<String, Integer>> values = new ArrayList<>();
        for (String permission : permissions){
            Map<String, Integer> value = new HashMap<>();
            value.put("role_id", id);
            value.put("permission_id", Integer.parseInt(permission));
            values.add(value);
        }
        roleService.deleteRolePermissionByRoleId(id);
        int i = roleService.updateRolePermisson(values);
        return MapUtil.getReturnMapByNum(i,"修改");
    }

    private Role getRole(String json){
        Role role = new Role();
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        String id = jsonMap.get("id");
        String roleName = jsonMap.get("roleName");
        String description = jsonMap.get("description");
        if ((id != null) && (!"".equals(id))) {
            role.setId(Integer.parseInt(id));
        }
        role.setRoleName(roleName);
        role.setDescription(description);

        return role;
    }
}
