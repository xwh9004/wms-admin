package com.wms.admin.vo;

import lombok.Data;

@Data
public class DiscardDetailRecordVO extends BaseVO {
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

    private String type;
    /**
     * 供应商
     */
    private String vendorName;

    private String prodName;

    /**
     * 产品数量
     */
    private Integer prodAmount;
    /**
     * 货物单价
     */
    private Money unitPrice;


    /**
     * 入库状态
     */
    private String status;
}
