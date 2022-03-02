package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;
import com.wms.admin.vo.Money;

/**
 * <p>
 * 调拨详情记录表
 * </p>
 *
 * @author Jesse
 * @since 2022-02-18 12:17:42
 */
@TableName("t_wms_shift_detail_record")
public class ShiftDetailRecordEntity extends BaseEntity {

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

    @TableField("prod_unit_price")
    private Money prodUnitPrice;

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

    public Money getProdUnitPrice() {
        return prodUnitPrice;
    }

    public void setProdUnitPrice(Money prodUnitPrice) {
        this.prodUnitPrice = prodUnitPrice;
    }

    @Override
    public String toString() {
        return "ShiftDetailRecordEntity{" +
            "id=" + id +
            ", receiptId=" + receiptId +
            ", prodId=" + prodId +
            ", prodAmount=" + prodAmount +
            ", status=" + status +
            ", prodUnitPrice=" + prodUnitPrice +

        "}";
    }
}
