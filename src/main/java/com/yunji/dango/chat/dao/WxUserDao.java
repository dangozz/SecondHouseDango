package com.yunji.dango.chat.dao;

import com.yunji.dango.chat.model.WxUser;
import com.yunji.dango.shiro.dao.BaseDao;

public interface WxUserDao extends BaseDao<WxUser>{
    Integer insertWxUser(WxUser paramWxUser);
}
