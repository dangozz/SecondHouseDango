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
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getDetailAddress()
    {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress)
    {
        this.detailAddress = detailAddress;
    }

    public Integer getParentId()
    {
        return parentId;
    }

    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }

    public String toString()
    {
        return "SecondHouseDetail{id=" + id + ", coreSelling='" + coreSelling + '\'' + ", proprietorState='" + proprietorState + '\'' + ", matching='" + matching + '\'' + ", longitude='" + longitude + '\'' + ", latitude='" + latitude + '\'' + ", userName='" + userName + '\'' + ", phone='" + phone + '\'' + ", city='" + city + '\'' + ", region='" + region + '\'' + ", detailAddress='" + detailAddress + '\'' + ", parentId=" + parentId + '}';
    }
}
