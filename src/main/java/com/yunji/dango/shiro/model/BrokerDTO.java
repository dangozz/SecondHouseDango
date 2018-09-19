package com.yunji.dango.shiro.model;

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

    public BrokerDTO(){}
}
