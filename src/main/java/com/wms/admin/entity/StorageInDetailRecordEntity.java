package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;
import java.time.LocalDateTime;

/**
 * <p>
 * 入库详情记录表
 * </p>
 *
 * @author Jesse
 * @since 2022-02-17 13:02:57
 */
@TableName("t_wms_storage_in_detail_record")
public class StorageInDetailRecordEntity extends BaseEntity {

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
     * 货架号
     */
    @TableField("rack_id")
    private String rackId;

    /**
     * 占用货架数量
     */
    @TableField("rack_takes")
    private Integer rackTakes;

    /**
     * 入库状态
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
    public String getRackId() {
        return rackId;
    }

    public void setRackId(String rackId) {
        this.rackId = rackId;
    }
    public Integer getRackTakes() {
        return rackTakes;
    }

    public void setRackTakes(Integer rackTakes) {
        this.rackTakes = rackTakes;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "StorageInDetailRecordEntity{" +
                "id=" + id +
                ", receiptId=" + receiptId +
                ", prodId=" + prodId +
                ", prodAmount=" + prodAmount +
                ", rackId=" + rackId +
                ", rackTakes=" + rackTakes +
                ", status=" + status +
                "}";
    }
}
