package com.wms.admin.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoragesRegionQueryVO {

    /**
     * 仓库名称
     */
    private String regionName;

    private String regionNo;
    /**
     * 仓库类型 1 一级库 2 二级库
     */
    private String regionType;




}
