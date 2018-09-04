package com.yunji.dango.shiro.uti;

import java.util.HashMap;
import java.util.Map;

public class MapUtil{
    public static Map getReturnMapByNum(Integer num, String name){
        Map<String, Object> map = new HashMap<>();
        if (num > 0){
            map.put("status", 200);
            map.put("message", name + "成功");
        }else{
            map.put("status", 400);
            map.put("message", name + "失败");
        }
        return map;
    }
}
