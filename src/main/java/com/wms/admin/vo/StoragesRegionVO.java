package com.wms.admin.vo;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Validated
@Data
public class StoragesRegionVO {

    private String id;
    /**
     * 仓库编号
     */
    private String regionNo;

    /**
     * 仓库名称
     */
    private String regionName;

    /**
     * 仓库类型 1 一级库 2 二级库
     */
    private String regionType;

    private String address;


    /**
     * 描述
     */
    private String description;
    private String createBy;
    private String updateBy;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
