package com.yunji.dango.chat.service.impl;

import com.yunji.dango.chat.dao.MessageDao;
import com.yunji.dango.chat.model.Message;
import com.yunji.dango.chat.service.MessageService;
import com.yunji.dango.shiro.service.impl.BaseServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public void setBaseDao() {
        super.setBaseDao(messageDao);
    }

    @Override
    public Integer insertMessage(Message message) {
        return messageDao.insertMessage(message);
    }

    @Override
    public Integer updateTypeByID(Integer id, Integer type) {
        return messageDao.updateTypeByID(id, type);
    }

    @Override
    public Integer updateTypeByToID(Integer fromId, Integer toId, Integer type) {
        return messageDao.updateTypeByToID(fromId, toId, type);
    }

    @Override
    public List<Message> findMessageByUserId(Integer user1_id, Integer user2_id) {
        return messageDao.findMessageByUserId(user1_id, user2_id);
    }

    @Override
    public List<Message> findChatList(Integer id) {
        return messageDao.findChatList(id);
    }

    @Override
    public Message findLastMessage(Integer id1, Integer id2) {
        return messageDao.findLastMessage(id1, id2);
    }
}
