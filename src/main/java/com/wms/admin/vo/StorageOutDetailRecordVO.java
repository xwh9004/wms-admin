package com.wms.admin.vo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class StorageOutDetailRecordVO extends BaseVO{
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
     * 产品类型
     */
    private String prodType;

    /**
     * 产品数量
     */
    private Integer prodAmount;
    /**
     * 单价
     */
    private Money unitPrice;
    /**
     * 出库状态
     */
    private String status;
}
