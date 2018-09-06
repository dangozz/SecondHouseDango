package com.yunji.dango.secondhouse.model;

import java.util.Date;

public class Collection{
    private Integer id;
    private Integer userId;
    private Integer targetId;
    private Date time;

    public Collection() {}

    public Collection(Integer userId, Integer targetId, Date time)
    {
        this.userId = userId;
        this.targetId = targetId;
        this.time = time;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getTargetId()
    {
        return targetId;
    }

    public void setTargetId(Integer targetId)
    {
        this.targetId = targetId;
    }

    public Date getTime()
    {
        return time;
    }

    public void setTime(Date time)
    {
        this.time = time;
    }

    public String toString()
    {
        return "Collection{id=" + this.id + ", userId='" + this.userId + '\'' + ", targetId='" + this.targetId + '\'' + ", time=" + this.time + '}';
    }
}
