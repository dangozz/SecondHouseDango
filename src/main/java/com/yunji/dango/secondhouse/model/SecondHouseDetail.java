package com.yunji.dango.secondhouse.model;

public class SecondHouseDetail{
    private Integer id;
    private String coreSelling;
    private String proprietorState;
    private String matching;
    private String longitude;
    private String latitude;
    private String userName;
    private String phone;
    private String city;
    private String region;
    private String detailAddress;
    private Integer parentId;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getCoreSelling()
    {
        return coreSelling;
    }

    public void setCoreSelling(String coreSelling)
    {
        this.coreSelling = coreSelling;
    }

    public String getProprietorState()
    {
        return proprietorState;
    }

    public void setProprietorState(String proprietorState)
    {
        this.proprietorState = proprietorState;
    }

    public String getMatching()
    {
        return matching;
    }

    public void setMatching(String matching)
    {
        this.matching = matching;
    }

    public String getLongitude()
    {
        return this.longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatitude()
    {
        return this.latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getUserName()
    {
        return this.userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getCity()
    {
        return this.city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getRegion()
    {
        return this.region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getDetailAddress()
    {
        return this.detailAddress;
    }

    public void setDetailAddress(String detailAddress)
    {
        this.detailAddress = detailAddress;
    }

    public Integer getParentId()
    {
        return this.parentId;
    }

    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }

    public String toString()
    {
        return "SecondHouseDetail{id=" + this.id + ", coreSelling='" + this.coreSelling + '\'' + ", proprietorState='" + this.proprietorState + '\'' + ", matching='" + this.matching + '\'' + ", longitude='" + this.longitude + '\'' + ", latitude='" + this.latitude + '\'' + ", userName='" + this.userName + '\'' + ", phone='" + this.phone + '\'' + ", city='" + this.city + '\'' + ", region='" + this.region + '\'' + ", detailAddress='" + this.detailAddress + '\'' + ", parentId=" + this.parentId + '}';
    }
}
