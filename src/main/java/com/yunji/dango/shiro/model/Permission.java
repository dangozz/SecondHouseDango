package com.yunji.dango.shiro.model;

public class Permission{
    private Integer id;
    private String permissionName;
    private String description;
    private String type;
    private Integer parentId;
    private String url;
    private Integer level;

    public Permission() {}

    public Permission(Integer id, String permissionName, String description, String type, Integer parentId, String url, Integer level){
        this.id = id;
        this.permissionName = permissionName;
        this.description = description;
        this.type = type;
        this.parentId = parentId;
        this.url = url;
        this.level = level;
    }

    public Permission(String permissionName, String description, String type, Integer parentId, String url, Integer level){
        this.permissionName = permissionName;
        this.description = description;
        this.type = type;
        this.parentId = parentId;
        this.url = url;
        this.level = level;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getPermissionName(){
        return permissionName;
    }

    public void setPermissionName(String permissionName){
        this.permissionName = permissionName;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public Integer getParentId(){
        return parentId;
    }

    public void setParentId(Integer parentId){
        this.parentId = parentId;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public Integer getLevel(){
        return level;
    }

    public void setLevel(Integer level){
        this.level = level;
    }

    public String toString(){
        return "Permission{id=" + id + ", permissionName='" + permissionName + '\'' + ", description='" + description + '\'' + ", type='" + type + '\'' + ", parentId=" + parentId + ", url='" + url + '\'' + ", level=" + level + '}';
    }
}
