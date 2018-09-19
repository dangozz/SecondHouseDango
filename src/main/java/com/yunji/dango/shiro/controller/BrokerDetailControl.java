package com.yunji.dango.shiro.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunji.dango.chat.model.WxUser;
import com.yunji.dango.chat.service.WxUserService;
import com.yunji.dango.shiro.model.Admin;
import com.yunji.dango.shiro.model.BrokerDTO;
import com.yunji.dango.shiro.model.BrokerDetail;
import com.yunji.dango.shiro.service.AdminService;
import com.yunji.dango.shiro.service.BrokerDetailService;
import com.yunji.dango.shiro.uti.LOG;
import com.yunji.dango.shiro.uti.MD5;
import com.yunji.dango.shiro.uti.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author: DANGO
 * @date 2018/9/6 11:25
 * @Description:
 */
@RestController
@RequestMapping("/shiro")
public class BrokerDetailControl {
    @Autowired
    private BrokerDetailService brokerDetailService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private WxUserService wxUserService;

    @RequestMapping("/addBrokerDetail.do")
    public Map addBrokerDetail(@RequestBody String json){
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        BrokerDetail brokerDetail=getBrokerDetail(json);
        String phone=jsonMap.get("phone");
        String password=jsonMap.get("password");
        String idCard=jsonMap.get("idCard");
        String wxUserId=jsonMap.get("wxUserId");

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", 400);
        resultMap.put("message", "该微信用户已注册");

        Map<String,String> wxUserIdMap=new HashMap<>(2);
        wxUserIdMap.put("wxUserId",wxUserId);
        BrokerDetail bd=brokerDetailService.findOneModel(wxUserIdMap);
        if(bd!=null){
            return resultMap;
        }

        Map<String,String> phoneMap=new HashMap<>(2);
        phoneMap.put("phone",phone);
        Admin phoneAdmin=adminService.findOneModel(phoneMap);
        if(phoneAdmin!=null){
            resultMap.put("message","手机号已注册");
            return resultMap;
        }
        Map<String,String> idCardMap=new HashMap<>(2);
        idCardMap.put("idCard",idCard);
        BrokerDetail idCardBroker=brokerDetailService.findOneModel(idCardMap);
        if(idCardBroker!=null){
            resultMap.put("message","身份证号已注册");
            return resultMap;
        }

        Admin admin=new Admin();
        admin.setName(brokerDetail.getName());
        admin.setPhone(phone);
        admin.setPassword(MD5.MD5Encode(password));
        int i1=adminService.insertModel(admin);
        brokerDetail.setAdminId(admin.getId());

        WxUser wxUser=new WxUser();
        wxUser.setId(brokerDetail.getWxUserId());
        wxUser.setPhone(phone);
        int i2=wxUserService.updateModel(wxUser);

        int i=brokerDetailService.insertModel(brokerDetail);
        if(i1==i2&&i2==1) {
            return MapUtil.getReturnMapByNum(i, "新增");
        }else {
            return MapUtil.getReturnMapByNum(0,"新增");
        }
    }

    @RequestMapping("/findAllBrokerDetail.do")
    public List findAllBrokerDetail(@RequestBody String json){
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        Integer page = Integer.parseInt(jsonMap.get("page"));
        Integer num = Integer.parseInt(jsonMap.get("num"));

        Page page_ = PageHelper.startPage(page, num, true);
        List<BrokerDetail> list = brokerDetailService.findAllModel();

        List<BrokerDTO> resultList = new ArrayList<>();
        for(BrokerDetail brokerDetail:list){
            BrokerDTO brokerDTO=new BrokerDTO();
//            WxUser wxUser=wxUserService.findModelById(brokerDetail.getWxUserId());
            Map<String,String> wxUserIdMap=new HashMap<>(2);
            wxUserIdMap.put("id",brokerDetail.getWxUserId()+"");
            WxUser wxUser=wxUserService.findOneModel(wxUserIdMap);
            brokerDTO.setId(brokerDetail.getId());
            brokerDTO.setWxUserId(brokerDetail.getWxUserId());
            brokerDTO.setSeniority(brokerDetail.getSeniority());
            brokerDTO.setName(brokerDetail.getName());
            brokerDTO.setArea(brokerDetail.getArea());
            brokerDTO.setIdCard(brokerDetail.getIdCard());
            brokerDTO.setImage(wxUser.getImage());
            brokerDTO.setCompany(wxUser.getCompany());
            brokerDTO.setCreateTime(brokerDetail.getCreateTime());
            resultList.add(brokerDTO);
        }
        return resultList;
    }

    @RequestMapping("/findBrokerDetailById.do")
    public Map findBrokerDetailById(@RequestBody String json) {
        Map<String, String> jsonMap = new Gson().fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
        String id = jsonMap.get("id");
        Map<String, Object> resultMap = new HashMap<>();
        if (id != null && !"".equals(id)) {
            BrokerDetail brokerDetail=brokerDetailService.findModelById(Integer.parseInt(id));
//            WxUser wxUser=wxUserService.findModelById(brokerDetail.getWxUserId());
            Map<String,String> wxUserIdMap=new HashMap<>(2);
            wxUserIdMap.put("id",brokerDetail.getWxUserId()+"");
            WxUser wxUser=wxUserService.findOneModel(wxUserIdMap);
            resultMap.put("brokerDetail",brokerDetail);
            resultMap.put("wxUser",wxUser);
            resultMap.put("status", 200);
        } else {
            resultMap.put("status", 400);
            resultMap.put("message","查询失败");
        }
        return resultMap;
    }


    public BrokerDetail getBrokerDetail(String json){
        BrokerDetail brokerDetail=new BrokerDetail();
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        String id=jsonMap.get("id");
        String wxUserId=jsonMap.get("wxUserId");
        String adminId=jsonMap.get("adminId");
        String seniority=jsonMap.get("seniority");
        String name=jsonMap.get("name");
        String idCard=jsonMap.get("idCard");
        String area=jsonMap.get("area");

        if (id != null && !"".equals(id)) {
            brokerDetail.setId(Integer.parseInt(id));
        }
        if (wxUserId != null && !"".equals(wxUserId)) {
            brokerDetail.setWxUserId(Integer.parseInt(wxUserId));
        }
        if (adminId != null && !"".equals(adminId)) {
            brokerDetail.setAdminId(Integer.parseInt(adminId));
        }
        if (seniority != null && !"".equals(seniority)) {
            brokerDetail.setSeniority(Integer.parseInt(seniority));
        }
        brokerDetail.setName(name);
        brokerDetail.setIdCard(idCard);
        brokerDetail.setArea(area);
        brokerDetail.setCreateTime(new Date());
        return brokerDetail;
    }

}
