package com.wms.admin.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReceiptRecordQueryVO {

    /**
     * 单据编号
     */
    private String receiptNo;

    /**
     * 单据类型
     */
    private String receiptType;

    /**
     * 记录类型
     */
    private String subType;

    /**
     * 收货出货库
     */
    private String regionId;

    /**
     * 申请人工号
     */
    private String applyId;

    /**
     * 经办人工号
     */
    private String operatorId;


    /**
     * 申请时间
     */
    private LocalDateTime applyStartDate;
    private LocalDateTime applyEndDate;
}
