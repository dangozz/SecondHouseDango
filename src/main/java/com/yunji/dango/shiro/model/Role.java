package com.yunji.dango.shiro.model;

public class Role{
    private Integer id;
    private String roleName;
    private String description;

    public Role() {}

    public Role(Integer id, String roleName, String description){
        this.id = id;
        this.roleName = roleName;
        this.description = description;
    }

    public Role(String roleName, String description){
        this.roleName = roleName;
        this.description = description;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getRoleName(){
        return roleName;
    }

    public void setRoleName(String roleName){
        this.roleName = roleName;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String toString(){
        return "Role{id=" + id + ", roleName='" + roleName + '\'' + ", description='" + description + '\'' + '}';
    }
}
