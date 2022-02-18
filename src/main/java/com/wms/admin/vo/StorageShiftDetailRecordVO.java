package com.wms.admin.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StorageShiftDetailRecordVO {
    /**
     * 主键id
     */
    private String id;
    /**
     * 单据ID
     */
    private String receiptId;

    /**
     * 产品ID
     */
    private String prodId;

    private String prodNo;
    /**
     * 供应商
     */
    private String vendorName;
    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 产品数量
     */
    private Integer prodAmount;
    /**
     * 出库状态
     */
    private String status;
    private String createBy;
    private String updateBy;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
