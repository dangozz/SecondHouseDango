package com.yunji.dango.chat.model;

public class Message{
    private Integer id;
    private Integer fromId;
    private Integer toId;
    private String message;
    private Integer type;
    private String time;
    private Integer projectId;

    public Message() {}

    public Message(Integer fromId, Integer toId, String message, Integer type, String time){
        this.fromId = fromId;
        this.toId = toId;
        this.message = message;
        this.type = type;
        this.time = time;
    }

    public Message(Integer fromId, Integer toId, String message, Integer type, String time, Integer projectId){
        this.fromId = fromId;
        this.toId = toId;
        this.message = message;
        this.type = type;
        this.time = time;
        this.projectId = projectId;
    }

    public Message(Integer id, Integer fromId, Integer toId, String message, Integer type, String time, Integer projectId){
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.message = message;
        this.type = type;
        this.time = time;
        this.projectId = projectId;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getFromId(){
        return fromId;
    }

    public void setFromId(Integer fromId){
        this.fromId = fromId;
    }

    public Integer getToId(){
        return toId;
    }

    public void setToId(Integer toId){
        this.toId = toId;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public Integer getType(){
        return type;
    }

    public void setType(Integer type){
        this.type = type;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public Integer getProjectId(){
        return projectId;
    }

    public void setProjectId(Integer projectId){
        this.projectId = projectId;
    }

    public String toString(){
        return "Message{id=" + this.id + ", fromId=" + this.fromId + ", toId=" + this.toId + ", message='" + this.message + '\'' + ", type=" + this.type + ", time='" + this.time + '\'' + ", projectId='" + this.projectId + '\'' + '}';
    }
}
