package com.yunji.dango.chat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunji.dango.chat.model.Message;
import com.yunji.dango.chat.service.MessageService;
import com.yunji.dango.shiro.uti.LOG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: DANGO
 * @date 2018/8/20 9:08
 * @Description:
 */
//@Component
@ServerEndpoint(value = "/chat/{user}")
public class WebSocketChat {

//    @Autowired
//    private MessageService messageService;

    private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    private MessageService messageService= (MessageService) ac.getBean("messageService");

    private static int onlineCount = 0;

    private static ConcurrentHashMap<String,WebSocketChat> webSocketTests = new ConcurrentHashMap<>();

    private Session session;

    @OnOpen
    public void onOpen(@PathParam("user") String user, Session session) {
        WebSocketChat chat=webSocketTests.get(user);
        if(chat==null) {
            this.session = session;
            webSocketTests.put(user, this);
        }else {
            chat.session=session;
        }
        addOnlineCount();
        LOG.logger.info("新连接接入！" + user + "  当前在线人数：" + getOnlineCount());
    }

    @OnClose
    public void onClose() {
        webSocketTests.remove(this);
        subOnlineCount();
        LOG.logger.info("关闭连接！当前在线人数：" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");

        LOG.logger.info("接收到的消息：" + message);
        Map<String,String> map=new Gson().fromJson(message,new TypeToken<Map<String,String>>(){}.getType());

        Integer fromId=Integer.parseInt(map.get("fromId"));
        Integer toId=Integer.parseInt(map.get("toId"));
//        Integer projectId=Integer.parseInt(map.get("projectId"));

        Message message_=new Message(fromId,toId,map.get("message"),0,sdf.format(new Date()));
        int num=messageService.insertMessage(message_);

        String user=map.get("toId");
        WebSocketChat toSocket=webSocketTests.get(user);
        try {
            if(num==1) {
//                session.getBasicRemote().sendText("message_ok");
                if (toSocket != null) {
                    toSocket.session.getBasicRemote().sendText(map.get("message"));
                    messageService.updateTypeByID(message_.getId(), 1);
//                session.getBasicRemote().sendText(map.get("message")+"  send ok!");
                } else {
//                session.getBasicRemote().sendText(map.get("user")+"未上线");
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            LOG.logger.error(e.getMessage());
        }
    }

    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        WebSocketChat.onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        WebSocketChat.onlineCount--;
    }

}
