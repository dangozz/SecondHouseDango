package com.yunji.dango.secondhouse.model;

import java.util.Date;

public class SecondHandHouse{
    private Integer id;
    private String titel;
    private String price;
    private String houseType;
    private String area;
    private String univalent;
    private String orientation;
    private String floor;
    private String decoration;
    private String indexImg;
    private long flag;
    private String type;
    private String year;
    private String region;
    private String village;
    private Integer auditState;
    private String shelfState;
    private Date dateTime;

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getTitel()
    {
        return this.titel;
    }

    public void setTitel(String titel)
    {
        this.titel = titel;
    }

    public String getPrice()
    {
        return this.price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getHouseType()
    {
        return this.houseType;
    }

    public void setHouseType(String houseType)
    {
        this.houseType = houseType;
    }

    public String getArea()
    {
        return this.area;
    }

    public void setArea(String area)
    {
        this.area = area;
    }

    public String getUnivalent()
    {
        return this.univalent;
    }

    public void setUnivalent(String univalent)
    {
        this.univalent = univalent;
    }

    public String getOrientation()
    {
        return this.orientation;
    }

    public void setOrientation(String orientation)
    {
        this.orientation = orientation;
    }

    public String getFloor()
    {
        return this.floor;
    }

    public void setFloor(String floor)
    {
        this.floor = floor;
    }

    public String getDecoration()
    {
        return this.decoration;
    }

    public void setDecoration(String decoration)
    {
        this.decoration = decoration;
    }

    public String getIndexImg()
    {
        return this.indexImg;
    }

    public void setIndexImg(String indexImg)
    {
        this.indexImg = indexImg;
    }

    public long getFlag()
    {
        return this.flag;
    }

    public void setFlag(long flag)
    {
        this.flag = flag;
    }

    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getYear()
    {
        return this.year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public String getRegion()
    {
        return this.region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getVillage()
    {
        return this.village;
    }

    public void setVillage(String village)
    {
        this.village = village;
    }

    public Integer getAuditState()
    {
        return this.auditState;
    }

    public void setAuditState(Integer auditState)
    {
        this.auditState = auditState;
    }

    public String getShelfState()
    {
        return this.shelfState;
    }

    public void setShelfState(String shelfState)
    {
        this.shelfState = shelfState;
    }

    public Date getDateTime()
    {
        return this.dateTime;
    }

    public void setDateTime(Date dateTime)
    {
        this.dateTime = dateTime;
    }

    public String toString()
    {
        return "SecondHandHouse{id=" + this.id + ", titel='" + this.titel + '\'' + ", price='" + this.price + '\'' + ", houseType='" + this.houseType + '\'' + ", area='" + this.area + '\'' + ", univalent='" + this.univalent + '\'' + ", orientation='" + this.orientation + '\'' + ", floor='" + this.floor + '\'' + ", decoration='" + this.decoration + '\'' + ", indexImg='" + this.indexImg + '\'' + ", flag=" + this.flag + ", type='" + this.type + '\'' + ", year='" + this.year + '\'' + ", region='" + this.region + '\'' + ", village='" + this.village + '\'' + ", auditState=" + this.auditState + ", shelfState='" + this.shelfState + '\'' + ", dateTime=" + this.dateTime + '}';
    }
}
