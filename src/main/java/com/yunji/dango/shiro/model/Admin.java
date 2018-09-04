package com.yunji.dango.shiro.model;

public class Admin{
    private int id;
    private String password;
    private String name;
    private String phone;
    private String position;
    private String department;
    private String openid;

    public Admin() {}

    public Admin(String password, String name, String phone, String position, String department, String openid){
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.position = position;
        this.department = department;
        this.openid = openid;
    }

    public Admin(String password, String name, String phone, String position, String department){
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.position = position;
        this.department = department;
    }

    public Admin(int id, String password, String name, String phone, String position, String department){
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.position = position;
        this.department = department;
    }

    public String getOpenid(){
        return openid;
    }

    public void setOpenid(String openid){
        this.openid = openid;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPosition(){
        return position;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public String getDepartment(){
        return department;
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public String toString(){
        return "Admin{id=" + id + ", password='" + password + '\'' + ", name='" + name + '\'' + ", phone='" + phone + '\'' + ", position='" + position + '\'' + ", department='" + department + '\'' + ", openid='" + openid + '\'' + '}';
    }
}
