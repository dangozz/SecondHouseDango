package com.yunji.dango.chat.model;

import java.io.Serializable;

public class WxUser implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String openid;
    private String name;
    private String image;
    private String phone;
    private String friend;
    private String company;

    public WxUser() {}

    public WxUser(String openid, String name, String image, String phone, String friend, String company){
        this.openid = openid;
        this.name = name;
        this.image = image;
        this.phone = phone;
        this.friend = friend;
        this.company = company;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getOpenid(){
        return openid;
    }

    public void setOpenid(String openid){
        this.openid = openid;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getFriend(){
        return friend;
    }

    public void setFriend(String friend){
        this.friend = friend;
    }

    public String getCompany(){
        return company;
    }

    public void setCompany(String company){
        this.company = company;
    }

    public String toString(){
        return "WxUser{id=" + this.id + ", openid='" + this.openid + '\'' + ", name='" + this.name + '\'' + ", image='" + this.image + '\'' + ", phone='" + this.phone + '\'' + ", friend='" + this.friend + '\'' + ", company='" + this.company + '\'' + '}';
    }
}
