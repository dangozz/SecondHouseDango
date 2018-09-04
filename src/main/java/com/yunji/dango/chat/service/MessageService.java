package com.yunji.dango.chat.service;

import com.yunji.dango.chat.model.Message;
import com.yunji.dango.shiro.service.BaseService;
import java.util.List;

public interface MessageService extends BaseService<Message>{
    Integer insertMessage(Message paramMessage);

    Integer updateTypeByID(Integer paramInteger1, Integer paramInteger2);

    Integer updateTypeByToID(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3);

    List<Message> findMessageByUserId(Integer paramInteger1, Integer paramInteger2);

    List<Message> findChatList(Integer id);

    Message findLastMessage(Integer id1, Integer id2);
}
