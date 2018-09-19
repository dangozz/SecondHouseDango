package com.yunji.dango.chat.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunji.dango.chat.model.Message;
import com.yunji.dango.chat.model.WxUser;
import com.yunji.dango.chat.service.MessageService;

import java.util.*;

import com.yunji.dango.chat.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/ChatControl"})
public class ChatControl {
    @Autowired
    private MessageService messageService;
    @Autowired
    private WxUserService wxUserService;

    @RequestMapping({"/findMessageCollection.do"})
    public List findMessageCollection(@RequestBody String json) {
        Map<String, String> jsonMap = new Gson().fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
        String userId1 = jsonMap.get("userId1");
        String userId2 = jsonMap.get("userId2");
        List<Message> messages = this.messageService.findMessageByUserId(Integer.parseInt(userId1), Integer.parseInt(userId2));
        for (Message message : messages) {
            if (message.getType() == 1) {
                this.messageService.updateTypeByID(message.getId(), 2);
            }
        }
        return messages;
    }

    @RequestMapping("/findChatList.do")
    public List findChatList(@RequestBody String json) {
        Map<String, String> jsonMap = new Gson().fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
        Integer id = Integer.parseInt(jsonMap.get("id"));
        List<Message> messageIds = messageService.findChatList(id);
        LinkedHashSet<Integer> ids = new LinkedHashSet<>();
        for (Message message_ : messageIds) {
            if (!message_.getFromId().equals(id))
                ids.add(message_.getFromId());
            else if (!message_.getToId().equals(id)) {
                ids.add(message_.getToId());
            }
        }
        List<Map<String, Object>> returnList = new ArrayList<>();
        for (Integer i : ids) {
            Map<String, Object> map = new HashMap<>();
            Message message = messageService.findLastMessage(i, id);
            Map<String, String> idMap = new HashMap<>();
            idMap.put("id", "" + i);
            WxUser wxUser = wxUserService.findOneModel(idMap);
            map.put("user", wxUser);
            map.put("message", message);
            returnList.add(map);
        }
        return returnList;
    }

    @RequestMapping("/findChatNumber.do")
    public Map findChatNumber(@RequestBody String json) {
        Map<String, Object> resultMap = new HashMap<>(3);
        Map<String, String> jsonMap = new Gson().fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
        Integer id = Integer.parseInt(jsonMap.get("id"));
        List<Message> messageIds = messageService.findChatList(id);
        LinkedHashSet<Integer> allChat = new LinkedHashSet<>();
        for (Message message : messageIds) {
            if (message.getFromId().equals(id)) {
                allChat.add(message.getToId());
            } else if (message.getToId().equals(id)) {
                allChat.add(message.getFromId());
            }
        }
        if (allChat.size() != 0) {
            int n = 0;
            for (Integer i : allChat) {
                Message message = messageService.findLastMessage(i, id);
                if (message.getFromId().equals(id))
                    n++;
            }
            int percentage = 100 * n / allChat.size();
            resultMap.put("num", allChat.size());
            resultMap.put("percentage", percentage);
        } else {
            resultMap.put("num", 0);
            resultMap.put("percentage", 0);
        }
        return resultMap;
    }
}
