package com.yunji.dango.secondhouse.contorller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunji.dango.secondhouse.model.Collection;
import com.yunji.dango.secondhouse.model.SecondHandHouse;
import com.yunji.dango.secondhouse.model.SecondHouseDetail;
import com.yunji.dango.secondhouse.model.SecondHouseImage;
import com.yunji.dango.secondhouse.service.CollectionService;
import com.yunji.dango.secondhouse.service.SecondHandHouseService;
import com.yunji.dango.secondhouse.service.SecondHouseDetailService;
import com.yunji.dango.secondhouse.service.SecondHouseImageService;
import com.yunji.dango.shiro.uti.MapUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/Collection"})
public class CollectionControl{
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private SecondHandHouseService secondHandHouseService;
    @Autowired
    private SecondHouseDetailService secondHouseDetailService;
    @Autowired
    private SecondHouseImageService secondHouseImageService;

    @RequestMapping({"/collection.do"})
    public Map collection(@RequestBody String json){
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        Integer userId = Integer.parseInt(jsonMap.get("id"));
        Integer targetId = Integer.parseInt(jsonMap.get("targetId"));
        Date time = new Date();
        Collection collection = new Collection(userId, targetId, time);
        int i = this.collectionService.insertModel(collection);
        return MapUtil.getReturnMapByNum(i, "收藏");
    }

    @RequestMapping({"/delCollection.do"})
    public Map delCollection(@RequestBody String json){
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        String userId = jsonMap.get("id");
        String targetId = jsonMap.get("targetId");
        Collection collection = new Collection();
        collection.setUserId(Integer.parseInt(userId));
        collection.setTargetId(Integer.parseInt(targetId));
        int num = this.collectionService.deleteModel(collection);
        return MapUtil.getReturnMapByNum(num, "删除");
    }

    @RequestMapping({"/findOneCollection.do"})
    public Map findOneCollection(@RequestBody String json){
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        String userId = jsonMap.get("id");
        String targetId = jsonMap.get("targetId");
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("targetId", targetId);
        List<Collection> list = this.collectionService.findModelByCondition(map);

        Map<String, Object> returnMap = new HashMap<>();
        if ((list != null) && (list.size() > 0)) {
            returnMap.put("num", list.size());
        } else {
            returnMap.put("num", 0);
        }
        return returnMap;
    }

    @RequestMapping({"/findCollection.do"})
    public List findCollection(@RequestBody String json){
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        String userId = jsonMap.get("id");

        Map<String, String> userIdMap = new HashMap<>();
        userIdMap.put("userId", userId);
        List<Collection> collections = this.collectionService.findModelByCondition(userIdMap);
        List<Map<String, Object>> returnList = new ArrayList<>();
        for (Collection collection : collections){
            Map<String, Object> recordsMap = new HashMap<>();
            Integer targetId = collection.getTargetId();

            SecondHandHouse secondHandHouse = secondHandHouseService.findModelById(targetId);
            SecondHouseDetail secondHouseDetail = secondHouseDetailService.findModelById(targetId);
            SecondHouseImage secondHouseImage = secondHouseImageService.findModelById(targetId);

            recordsMap.put("house", secondHandHouse);
            recordsMap.put("detail", secondHouseDetail);
            recordsMap.put("image", secondHouseImage);
            recordsMap.put("time", collection.getTime());

            returnList.add(recordsMap);
        }
        return returnList;
    }
}
