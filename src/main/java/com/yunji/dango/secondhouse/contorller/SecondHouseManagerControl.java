package com.yunji.dango.secondhouse.contorller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunji.dango.secondhouse.model.SecondHandHouse;
import com.yunji.dango.secondhouse.service.SecondHandHouseService;
import com.yunji.dango.secondhouse.service.SecondHouseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: DANGO
 * @date 2018/9/20 9:52
 * @Description:
 */
@RestController
@RequestMapping("/Manager")
public class SecondHouseManagerControl {

    @Autowired
    private SecondHandHouseService secondHandHouseService;

    @Autowired
    private SecondHouseDetailService secondHouseDetailService;

    @RequestMapping("/findAllSecondHouse.do")
    public List findAllSecondHouse(@RequestBody String json) {
        Map<String, String> jsonMap = new Gson().fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
        Integer page = Integer.parseInt(jsonMap.get("page"));
        Integer num = Integer.parseInt(jsonMap.get("num"));

        Page page_ = PageHelper.startPage(page, num, true);
        return secondHandHouseService.findAllModel();
    }

    @RequestMapping("/findAllSecondHouseByPhone.do")
    public List findAllSecondHouseByUserPhone(@RequestBody String json) {
        Map<String, String> jsonMap = new Gson().fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
        String phone = jsonMap.get("phone");
        List<SecondHandHouse> secondHandHouseList = new ArrayList<>();
        List<Integer> integerList = secondHouseDetailService.findParentIdByPhone(phone);
        for (Integer parntId : integerList) {
            SecondHandHouse secondHandHouse = secondHandHouseService.findModelById(parntId);
            if (secondHandHouse != null)
                secondHandHouseList.add(secondHandHouse);
        }
        return secondHandHouseList;
    }

    @RequestMapping("/updateExamine.do")
    public Map updateExamine(@RequestBody String json) {
        Map<String, String> jsonMap = new Gson().fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
        String examine = jsonMap.get("examine");
        String id = jsonMap.get("id");
        Map<String, Object> resultMap = new HashMap<>();
        int i = -1;
        try {
            Integer.parseInt(examine);
            Integer.parseInt(id);
            Map<String, String> examineMap = new HashMap<>(3);
            examineMap.put("examine", examine);
            examineMap.put("id", id);
            i = secondHandHouseService.updateExamine(examineMap);
        } catch (Exception e) {
            resultMap.put("status", 400);
            resultMap.put("message", "入参错误");
        }
        if (i == 1) {
            resultMap.put("status", 200);
            resultMap.put("message", "修改成功");
        } else if (i == 0) {
            resultMap.put("status", 400);
            resultMap.put("message", "修改失败");
        }
        return resultMap;
    }
}
