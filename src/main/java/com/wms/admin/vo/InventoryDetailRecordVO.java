package com.wms.admin.vo;

import lombok.Data;

@Data
public class InventoryDetailRecordVO extends BaseVO{
    private Integer id;

    /**
     * 单据ID
     */
    private String receiptId;

    /**
     * 产品ID
     */
    private String prodId;

    /**
     * 产品数量
     */
    private Integer prodAmount;

    /**
     * 产品单价
     */
    private Money prodUnitPrice;
    /**
     * 状态
     */
    private String status;
}
