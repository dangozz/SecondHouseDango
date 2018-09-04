package com.yunji.dango.chat.service;

import com.yunji.dango.chat.model.WxUser;
import com.yunji.dango.shiro.service.BaseService;

public interface WxUserService extends BaseService<WxUser>{
    Integer insertWxUser(WxUser paramWxUser);
}
