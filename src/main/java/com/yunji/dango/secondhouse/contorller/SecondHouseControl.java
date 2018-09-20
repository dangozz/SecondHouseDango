package com.yunji.dango.secondhouse.contorller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunji.dango.chat.model.WxUser;
import com.yunji.dango.chat.service.WxUserService;
import com.yunji.dango.redis.RedisCache;
import com.yunji.dango.secondhouse.model.BrowseRecord;
import com.yunji.dango.secondhouse.model.SecondHandHouse;
import com.yunji.dango.secondhouse.model.SecondHouseDetail;
import com.yunji.dango.secondhouse.model.SecondHouseImage;
import com.yunji.dango.secondhouse.service.BrowseRecordService;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/SecondHouse"})
public class SecondHouseControl{
    @Autowired
    private SecondHandHouseService secondHandHouseService;
    @Autowired
    private SecondHouseDetailService secondHouseDetailService;
    @Autowired
    private SecondHouseImageService secondHouseImageService;
    @Autowired
    private WxUserService wxUserService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private BrowseRecordService browseRecordService;

    @RequestMapping(value={"/findDetail.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public Map findDetail(Integer id, String openid){
        SecondHandHouse secondHandHouse = secondHandHouseService.findModelById(id);
        SecondHouseDetail secondHouseDetail = secondHouseDetailService.findModelById(id);
        SecondHouseImage secondHouseImage = secondHouseImageService.findModelById(id);

        WxUser wxUser = (WxUser)this.redisCache.getObject("wxuser" + openid);
        if ((wxUser == null) && (openid != null && !"".equals(openid))){
            Map<String, String> openidMap = new HashMap<>();
            openidMap.put("openid", openid);
            wxUser = wxUserService.findOneModel(openidMap);
        }
        Map<String, String> map = new HashMap<>();
        map.put("userId", "" + wxUser.getId());
        map.put("targetId", "" + id);
        BrowseRecord browseRecord = browseRecordService.findOneModel(map);
        if (browseRecord == null){
            browseRecord = new BrowseRecord(wxUser.getId(), id, new Date(), 1);
            browseRecordService.insertModel(browseRecord);
        }else{
            browseRecord.setRecordTime(new Date());
            browseRecordService.updateModel(browseRecord);
        }
//        String phone = wxUser.getPhone();
        Map<String, String> phoneMap = new HashMap<>();
        phoneMap.put("phone", secondHouseDetail.getPhone());
        WxUser houseUser = wxUserService.findOneModel(phoneMap);

        Map<String, Object> returnMap = new HashMap(5);
        returnMap.put("house", secondHandHouse);
        returnMap.put("detail", secondHouseDetail);
        returnMap.put("image", secondHouseImage);
        returnMap.put("houseUser", houseUser);

        return returnMap;
    }

    @RequestMapping({"/findRecords.do"})
    @ResponseBody
    public List findRecords(@RequestBody String json){
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        String id = jsonMap.get("id");
        Map<String, String> idMap = new HashMap<>();
        idMap.put("userId", id);
        List<BrowseRecord> browseRecords = this.browseRecordService.findModelByCondition(idMap);
        List<Map<String, Object>> returnList = new ArrayList<>();
        for (BrowseRecord browseRecord : browseRecords){
            Map<String, Object> recordsMap = new HashMap<>();
            Integer targetId = browseRecord.getTargetId();

            SecondHandHouse secondHandHouse = secondHandHouseService.findModelById(targetId);
            SecondHouseDetail secondHouseDetail = secondHouseDetailService.findModelById(targetId);
            SecondHouseImage secondHouseImage = secondHouseImageService.findModelById(targetId);

            recordsMap.put("house", secondHandHouse);
            recordsMap.put("detail", secondHouseDetail);
            recordsMap.put("image", secondHouseImage);
            recordsMap.put("time", browseRecord.getRecordTime());

            returnList.add(recordsMap);
        }
        return returnList;
    }

    @RequestMapping({"/delRecord.do"})
    @ResponseBody
    public Map delRecord(@RequestBody String json){
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        String userId = jsonMap.get("id");
        String targetId = jsonMap.get("targetId");
        BrowseRecord browseRecord = new BrowseRecord();
        browseRecord.setUserId(Integer.parseInt(userId));
        browseRecord.setTargetId(Integer.parseInt(targetId));
        int num = this.browseRecordService.deleteModel(browseRecord);
        return MapUtil.getReturnMapByNum(num, "删除");
    }
}
