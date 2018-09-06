package com.yunji.dango.secondhouse.model;

import java.util.Date;

public class BrowseRecord{
    private Integer id;
    private Integer userId;
    private Integer targetId;
    private Date recordTime;
    private Integer flag;

    public BrowseRecord() {}

    public BrowseRecord(Integer userId, Integer targetId, Date recordTime, Integer flag)
    {
        this.userId = userId;
        this.targetId = targetId;
        this.recordTime = recordTime;
        this.flag = flag;
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

    public Date getRecordTime()
    {
        return recordTime;
    }

    public void setRecordTime(Date recordTime)
    {
        this.recordTime = recordTime;
    }

    public Integer getFlag()
    {
        return flag;
    }

    public void setFlag(Integer flag)
    {
        this.flag = flag;
    }

    public String toString()
    {
        return "BrowseRecord{id=" + this.id + ", userId=" + this.userId + ", targetId=" + this.targetId + ", recordTime=" + this.recordTime + ", flag=" + this.flag + '}';
    }
}
