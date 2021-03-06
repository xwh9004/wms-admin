package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;
import com.wms.admin.vo.Money;

/**
 * <p>
 * 盘点详情录表
 * </p>
 *
 * @author Jesse
 * @since 2022-02-27 20:57:49
 */
@TableName("t_wms_inventory_detail_record")
public class InventoryDetailRecordEntity extends BaseEntity {

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
     * 产品单价
     */
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

    public Money getProdUnitPrice() {
        return prodUnitPrice;
    }

    public void setProdUnitPrice(Money prodUnitPrice) {
        this.prodUnitPrice = prodUnitPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "InventoryDetailRecordEntity{" +
            "id=" + id +
            ", receiptId=" + receiptId +
            ", prodId=" + prodId +
            ", prodAmount=" + prodAmount +
            ", prodUnitPrice=" + prodUnitPrice +
            ", status=" + status +
        "}";
    }
}
