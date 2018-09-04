package com.yunji.dango.secondhouse.model;

public class SecondHouseImage{
    private Integer id;
    private String description;
    private String imageUrl;
    private Integer parentId;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public Integer getParentId(){
        return parentId;
    }

    public void setParentId(Integer parentId){
        this.parentId = parentId;
    }

    public String toString(){
        return "SecondHouseImage{id=" + id + ", description='" + description + '\'' + ", imageUrl='" + imageUrl + '\'' + ", parentId=" + parentId + '}';
    }
}
