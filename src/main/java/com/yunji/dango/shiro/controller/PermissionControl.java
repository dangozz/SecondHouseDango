package com.yunji.dango.shiro.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunji.dango.shiro.model.Permission;
import com.yunji.dango.shiro.service.PermissionService;
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
public class PermissionControl{
    @Autowired
    private PermissionService permissionService;

    @RequestMapping({"/findAllPermission.do"})
    @ResponseBody
    public Map findAllPermission(@RequestBody String json){
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        Integer page = Integer.parseInt(jsonMap.get("page"));
        Integer num = Integer.parseInt(jsonMap.get("num"));

        Page page_ = PageHelper.startPage(page.intValue(), num.intValue(), true);

        List<Permission> list = permissionService.findAllModel();

        List<Permission> resultList = new ArrayList<>();
        resultList.addAll(list);

        Map<String, Object> map = new HashMap<>();
        map.put("admins", list);
        map.put("count", permissionService.findTotal());

        return map;
    }

    @RequestMapping({"/findOnePermission.do"})
    @ResponseBody
    public Permission findOnePermission(@RequestBody String json){
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        return permissionService.findOneModel(jsonMap);
    }

    @RequestMapping({"/addPermission.do"})
    @ResponseBody
    public Map addPermission(@RequestBody String json){
        Permission permission = getPermission(json);
        int i = permissionService.insertModel(permission);
        Map<String, Object> map = new HashMap<>();
        return MapUtil.getReturnMapByNum(i,"新增");
    }

    @RequestMapping({"/delPermission.do"})
    @ResponseBody
    public Map delPermission(@RequestBody String json){
        Permission permission = getPermission(json);

        int n = permissionService.deleteRolePermission(permission.getId());

        int i = permissionService.deleteModel(permission);
        Map<String, Object> map = new HashMap<>();
        if (i == 1){
            map.put("status", 200);
            map.put("message", "删除成功");
            map.put("num", n);
        }else{
            map.put("status", 400);
            map.put("message", "删除失败");
        }
        return map;
    }

    @RequestMapping({"/updatePermission.do"})
    @ResponseBody
    public Map updatePermission(@RequestBody String json){
        Permission permission = getPermission(json);
        int i = permissionService.updateModel(permission);
        Map<String, Object> map = new HashMap<>();
        return MapUtil.getReturnMapByNum(i,"修改");
    }

    private Permission getPermission(String json){
        Permission permission = new Permission();
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        String id = jsonMap.get("id");
        String permissionName = jsonMap.get("permissionName");
        String description = jsonMap.get("description");
        String type = jsonMap.get("type");
        String url = jsonMap.get("url");
        String parentId = jsonMap.get("parentId");
        String level = jsonMap.get("level");
        if ((id != null) && (!"".equals(id))) {
            permission.setId(Integer.parseInt(id));
        }
        permission.setPermissionName(permissionName);
        permission.setDescription(description);
        permission.setType(type);
        permission.setUrl(url);
        if ((parentId != null) && (!"".equals(parentId))) {
            permission.setParentId(Integer.parseInt(parentId));
        }
        if ((level != null) && (!"".equals(level))) {
            permission.setLevel(Integer.parseInt(level));
        }
        return permission;
    }
}
