package com.yunji.dango.chat.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunji.dango.chat.model.WxUser;
import com.yunji.dango.chat.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: DANGO
 * @date 2018/9/3 14:05
 * @Description:
 */
@RestController
@RequestMapping("/WxUser")
public class WxUserControl {

    @Autowired
    private WxUserService wxUserService;

    @RequestMapping("/findOneUser.do")
    public WxUser findOneUser(@RequestBody String json){
        Map<String, String> jsonMap = new Gson().fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
        String id=jsonMap.get("id");
        Map<String,String> idMap=new HashMap<>();
        idMap.put("id",id);
        WxUser wxUser=wxUserService.findOneModel(idMap);
        return wxUser;
    }
}
