package com.yunji.dango.shiro.model;

import com.yunji.dango.chat.model.WxUser;
import lombok.Data;

/**
 * @author: DANGO
 * @date 2018/9/18 10:43
 * @Description:
 */
@Data
public class BrokerDTO extends BrokerDetail{

    private String image;
    private String company;
    private String phone;

    public BrokerDTO(){}

    public BrokerDTO(BrokerDetail brokerDetail, WxUser wxUser){
        this.setId(brokerDetail.getId());
        this.setWxUserId(brokerDetail.getWxUserId());
        this.setSeniority(brokerDetail.getSeniority());
        this.setName(brokerDetail.getName());
        this.setArea(brokerDetail.getArea());
        this.setIdCard(brokerDetail.getIdCard());
        this.setImage(wxUser.getImage());
        this.setCompany(wxUser.getCompany());
        this.setCreateTime(brokerDetail.getCreateTime());
        this.setExamine(brokerDetail.getExamine());
        this.setPhone(wxUser.getPhone());
    }
}
