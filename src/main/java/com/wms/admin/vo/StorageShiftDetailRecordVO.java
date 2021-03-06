package com.wms.admin.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StorageShiftDetailRecordVO extends BaseVO {
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
    private String prodType;

    /**
     * 产品数量
     */
    private Integer prodAmount;
    private Money unitPrice;
    /**
     * 出库状态
     */
    private String status;
}
