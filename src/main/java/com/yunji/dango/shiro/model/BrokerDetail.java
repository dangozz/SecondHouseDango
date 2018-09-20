package com.yunji.dango.shiro.model;

import lombok.Data;

import java.util.Date;

/**
 * @author: DANGO
 * @date 2018/9/6 11:06
 * @Description:
 */
@Data
public class BrokerDetail {

    private Integer id;
    private Integer wxUserId;
    private Integer adminId;
    private Integer seniority;
    private String name;
    private String idCard;
    private String area;
    private Date createTime;
    private Integer examine;

}
