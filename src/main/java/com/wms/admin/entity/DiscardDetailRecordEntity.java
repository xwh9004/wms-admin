package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;

/**
 * <p>
 * 报废详情录表
 * </p>
 *
 * @author Jesse
 * @since 2022-02-26 14:22:49
 */
@TableName("t_wms_discard_detail_record")
public class DiscardDetailRecordEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 单据ID
     */
    @TableField("receipt_id")
    private String receiptId;

    /**
     * 产品ID
     */
    @TableField("prod_id")
    private String prodId;

    /**
     * 产品数量
     */
    @TableField("prod_amount")
    private Integer prodAmount;

    /**
     * 状态
     */
    @TableField("status")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }
    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }
    public Integer getProdAmount() {
        return prodAmount;
    }

    public void setProdAmount(Integer prodAmount) {
        this.prodAmount = prodAmount;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DiscardDetailRecordEntity{" +
            "id=" + id +
            ", receiptId=" + receiptId +
            ", prodId=" + prodId +
            ", prodAmount=" + prodAmount +
            ", status=" + status +
        "}";
    }
}
