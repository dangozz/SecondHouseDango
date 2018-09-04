package com.yunji.dango.chat.service.impl;

import com.yunji.dango.chat.dao.WxUserDao;
import com.yunji.dango.chat.model.WxUser;
import com.yunji.dango.chat.service.WxUserService;
import com.yunji.dango.shiro.service.impl.BaseServiceImpl;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxUserServiceImpl extends BaseServiceImpl<WxUser> implements WxUserService{
    @Autowired
    private WxUserDao wxUserDao;

    @Override
    public void setBaseDao(){
        super.setBaseDao(wxUserDao);
    }

    @Override
    public Integer insertWxUser(WxUser wxUser){
        return wxUserDao.insertWxUser(wxUser);
    }

    @Override
    public WxUser findOneModel(Map<String, String> map){
        return wxUserDao.findOneModel(map);
    }

    @Override
    public int updateModel(WxUser wxUser){
        return wxUserDao.updateModel(wxUser);
    }
}
