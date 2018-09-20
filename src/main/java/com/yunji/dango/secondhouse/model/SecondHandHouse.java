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
    private Integer examine;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getTitel()
    {
        return titel;
    }

    public void setTitel(String titel)
    {
        this.titel = titel;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getHouseType()
    {
        return houseType;
    }

    public void setHouseType(String houseType)
    {
        this.houseType = houseType;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea(String area)
    {
        this.area = area;
    }

    public String getUnivalent()
    {
        return univalent;
    }

    public void setUnivalent(String univalent)
    {
        this.univalent = univalent;
    }

    public String getOrientation()
    {
        return orientation;
    }

    public void setOrientation(String orientation)
    {
        this.orientation = orientation;
    }

    public String getFloor()
    {
        return floor;
    }

    public void setFloor(String floor)
    {
        this.floor = floor;
    }

    public String getDecoration()
    {
        return decoration;
    }

    public void setDecoration(String decoration)
    {
        this.decoration = decoration;
    }

    public String getIndexImg()
    {
        return indexImg;
    }

    public void setIndexImg(String indexImg)
    {
        this.indexImg = indexImg;
    }

    public long getFlag()
    {
        return flag;
    }

    public void setFlag(long flag)
    {
        this.flag = flag;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getVillage()
    {
        return village;
    }

    public void setVillage(String village)
    {
        this.village = village;
    }

    public Integer getAuditState()
    {
        return auditState;
    }

    public void setAuditState(Integer auditState)
    {
        this.auditState = auditState;
    }

    public String getShelfState()
    {
        return shelfState;
    }

    public void setShelfState(String shelfState)
    {
        this.shelfState = shelfState;
    }

    public Date getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(Date dateTime)
    {
        this.dateTime = dateTime;
    }

    public Integer getExamine() {
        return examine;
    }

    public void setExamine(Integer examine) {
        this.examine = examine;
    }

    public String toString()
    {
        return "SecondHandHouse{id=" + id + ", titel='" + titel + '\'' + ", price='" + price + '\'' + ", houseType='" + houseType + '\'' + ", area='" + area + '\'' + ", univalent='" + univalent + '\'' + ", orientation='" + orientation + '\'' + ", floor='" + floor + '\'' + ", decoration='" + decoration + '\'' + ", indexImg='" + indexImg + '\'' + ", flag=" + flag + ", type='" + type + '\'' + ", year='" + year + '\'' + ", region='" + this.region + '\'' + ", village='" + this.village + '\'' + ", auditState=" + this.auditState + ", shelfState='" + this.shelfState + '\'' + ", dateTime=" + this.dateTime + ", examine='" + this.examine + '\'' + '}';
    }
}
