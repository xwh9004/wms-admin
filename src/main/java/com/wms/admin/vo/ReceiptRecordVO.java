package com.wms.admin.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReceiptRecordVO<T> {
    /**
     * 主键id
     */
    private String id;

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
    private String regionName;

    /**
     * 调拨出货库
     */
    private String fromId;
    private String fromName;

    /**
     * 调拨入货库
     */
    private String toId;
    private String toName;

    /**
     * 申请人工号
     */
    private String applyId;

    /**
     * 经办人工号
     */
    private String operatorId;
    /**
     * 货物种数
     */
    private Integer prodTypeNums;

    /**
     * 货物总量
     */
    private Integer totalAmount;

    /**
     * 描述
     */
    private String description;

    /**
     * 申请时间
     */
    private LocalDateTime applyDate;
    private String createBy;
    private String updateBy;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<T> list;

}
