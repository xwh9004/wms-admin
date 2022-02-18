package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;
import java.time.LocalDateTime;

/**
 * <p>
 * 申请记录表
 * </p>
 *
 * @author Jesse
 * @since 2022-02-16 21:10:29
 */
@TableName("t_wms_receipt_record")
public class ReceiptRecordEntity extends BaseEntity {

    /**
     * 主键id
     */
    @TableId("id")
    private String id;

    /**
     * 单据编号
     */
    @TableField("receipt_no")
    private String receiptNo;

    /**
     * 单据类型
     */
    @TableField("receipt_type")
    private String receiptType;

    /**
     * 记录类型
     */
    @TableField("sub_type")
    private String subType;

    /**
     * 收货出货库
     */
    @TableField("region_id")
    private String regionId;

    /**
     * 调拨出货库
     */
    @TableField("from_id")
    private String fromId;

    /**
     * 调拨入货库
     */
    @TableField("to_id")
    private String toId;

    /**
     * 申请人工号
     */
    @TableField("apply_id")
    private String applyId;

    /**
     * 经办人工号
     */
    @TableField("operator_id")
    private String operatorId;

    /**
     * 货物种数
     */
    @TableField("prod_type_nums")
    private Integer prodTypeNums;

    /**
     * 货物总量
     */
    @TableField("total_amount")
    private Integer totalAmount;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 申请时间
     */
    @TableField("apply_date")
    private LocalDateTime applyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }
    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }
    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }
    public String getRegionId() {
        return regionId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }
    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
    public Integer getProdTypeNums() {
        return prodTypeNums;
    }

    public void setProdTypeNums(Integer prodTypeNums) {
        this.prodTypeNums = prodTypeNums;
    }
    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDateTime getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDateTime applyDate) {
        this.applyDate = applyDate;
    }

    @Override
    public String toString() {
        return "ReceiptRecordEntity{" +
            "id=" + id +
            ", receiptNo=" + receiptNo +
            ", receiptType=" + receiptType +
            ", subType=" + subType +
            ", regionId=" + regionId +
            ", fromId=" + fromId +
            ", toId=" + toId +
            ", applyId=" + applyId +
            ", operatorId=" + operatorId +
            ", prodTypeNums=" + prodTypeNums +
            ", totalAmount=" + totalAmount +
            ", description=" + description +
            ", applyDate=" + applyDate +
        "}";
    }
}
