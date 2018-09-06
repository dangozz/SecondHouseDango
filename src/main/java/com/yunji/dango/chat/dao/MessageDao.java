package com.yunji.dango.chat.dao;

import com.yunji.dango.chat.model.Message;
import com.yunji.dango.shiro.dao.BaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageDao extends BaseDao<Message>{
    Integer insertMessage(Message paramMessage);

    Integer updateTypeByID(@Param("id") Integer id, @Param("type") Integer type);

    Integer updateTypeByToID(@Param("fromId") Integer fromId, @Param("toId") Integer toId, @Param("type") Integer type);

    List<Message> findMessageByUserId(@Param("id1") Integer id1, @Param("id2") Integer id2);

    List<Message> findChatList(@Param("id") Integer id);

    Message findLastMessage(@Param("id1") Integer id1, @Param("id2") Integer id2);
}
