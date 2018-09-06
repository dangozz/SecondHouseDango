package com.yunji.dango.chat.service;

import com.yunji.dango.chat.model.Message;
import com.yunji.dango.shiro.service.BaseService;
import java.util.List;

public interface MessageService extends BaseService<Message>{
    Integer insertMessage(Message paramMessage);

    Integer updateTypeByID(Integer id, Integer type);

    Integer updateTypeByToID(Integer fromId, Integer toId, Integer type);

    List<Message> findMessageByUserId(Integer id1, Integer id2);

    List<Message> findChatList(Integer id);

    Message findLastMessage(Integer id1, Integer id2);
}
