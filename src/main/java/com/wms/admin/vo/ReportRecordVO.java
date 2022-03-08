package com.wms.admin.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportRecordVO {
    /**
     * 主键id
     */
    private String id;

    /**
     * 单据ID
     */
    private String receiptId;
    private String receiptNo;
    private String regionName;

    /**
     * 产品ID
     */
    private String prodId;

    private String prodNo;
    private String prodType;
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

    private String applyId;
    private LocalDateTime applyDate;

    private String fromName;
    private String toName;

    /**
     * 入库状态
     */
    private String status;
    private String createBy;
    private String updateBy;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
