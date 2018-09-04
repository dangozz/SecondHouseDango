package com.yunji.dango.chat.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunji.dango.chat.model.WxUser;
import com.yunji.dango.chat.service.WxUserService;
import com.yunji.dango.redis.RedisCache;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.yunji.dango.shiro.uti.LOG;
import com.yunji.dango.shiro.uti.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/wxlogin"})
public class WXLogin{
    @Autowired
    private WxUserService wxUserService;
    @Autowired
    private RedisCache redisCache;

    @RequestMapping({"/login.do"})
    @ResponseBody
    public Map Login(@RequestBody String json, HttpServletRequest request){
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        String code = jsonMap.get("code");
        String openid = "";
        try{
            URL url = new URL("https://api.weixin.qq.com/sns/jscode2session?appid=wxf66b7cbcfffaf267&secret=73bb80d896ceb00761f6d7ae7747c2cd&js_code=" + code + "&grant_type=authorization_code");
            URLConnection connection = url.openConnection();
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Map<String,String> resultMap=new Gson().fromJson(in.readLine(),new TypeToken<Map<String,String>>(){}.getType());
            openid = resultMap.get("openid");
        }catch (Exception e){
            e.printStackTrace();
            LOG.logger.error(e.getMessage());
        }
        Map<String, Object> map = new HashMap<>();
        if (("".equals(openid)) || (openid == null)){
            map.put("status", 400);
            map.put("message", "登录失败");
        }else{
            Map<String, String> openidMap = new HashMap<>();
            openidMap.put("openid", openid);
            WxUser wxUser = wxUserService.findOneModel(openidMap);
            if (wxUser == null){
                wxUser = new WxUser();
                wxUser.setOpenid(openid);
                this.wxUserService.insertWxUser(wxUser);
            }
            this.redisCache.put("wxuser" + openid, wxUser);

            map.put("status", 200);
            map.put("message", "登录成功");
            map.put("user", wxUser);
        }
        return map;
    }

    @RequestMapping({"/updateWxUser.do"})
    @ResponseBody
    public Map updateWxUser(@RequestBody String json){
        Map<String,String> jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
        String name = jsonMap.get("name");
        String image = jsonMap.get("image");
        String openid = jsonMap.get("openid");
        String phone = jsonMap.get("phone");
        String company = jsonMap.get("company");
        WxUser wxUser = new WxUser(openid, name, image, phone, null, company);
        int n = this.wxUserService.updateModel(wxUser);
        return MapUtil.getReturnMapByNum(n,"更新");
    }
}
