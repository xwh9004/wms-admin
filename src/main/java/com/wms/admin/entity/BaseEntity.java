package com.wms.admin.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    private String delFlag;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;
}
