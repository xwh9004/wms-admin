package com.wms.admin.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StorageInDetailRecordVO extends BaseVO{
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

    private String prodName;

    private String prodType;

    /**
     * 产品数量
     */
    private Integer prodAmount;

    private Money unitPrice;

    /**
     * 货架ID
     */
    private String rackId;
    /**
     * 货架号
     */
    private String rackNo;

    /**
     * 占用货架数量
     */
    private Integer rackTakes;

    /**
     * 入库状态
     */
    private String status;
}
