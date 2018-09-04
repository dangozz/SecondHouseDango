package com.yunji.dango.chat.dao;

import com.yunji.dango.chat.model.Message;
import com.yunji.dango.shiro.dao.BaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageDao extends BaseDao<Message>{
    Integer insertMessage(Message paramMessage);

    Integer updateTypeByID(@Param("id") Integer paramInteger1, @Param("type") Integer paramInteger2);

    Integer updateTypeByToID(@Param("fromId") Integer paramInteger1, @Param("toId") Integer paramInteger2, @Param("type") Integer paramInteger3);

    List<Message> findMessageByUserId(@Param("id1") Integer paramInteger1, @Param("id2") Integer paramInteger2);

    List<Message> findChatList(@Param("id") Integer id);

    Message findLastMessage(@Param("id1") Integer id1, @Param("id2") Integer id2);
}
