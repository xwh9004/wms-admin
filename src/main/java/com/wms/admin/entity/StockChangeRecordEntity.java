package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 产品库存变更记录表
 * </p>
 *
 * @author Jesse
 * @since 2022-03-08 19:58:52
 */
@TableName("t_wms_stock_change_record")
public class StockChangeRecordEntity extends BaseEntity {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;


    /**
     * 单据编号
     */
    @TableField("receipt_no")
    private String receiptNo;
    /**
     * 库区ID
     */
    @TableField("region_id")
    private String regionId;

    /**
     * 产品ID
     */
    @TableField("prod_id")
    private String prodId;

    /**
     * 当前库存
     */
    @TableField("current_stock")
    private Integer currentStock;

    /**
     * 变化库存
     */
    @TableField("change_stock")
    private Integer changeStock;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
    }

    public Integer getChangeStock() {
        return changeStock;
    }

    public void setChangeStock(Integer changeStock) {
        this.changeStock = changeStock;
    }

    @Override
    public String toString() {
        return "StockChangeRecordEntity{" +
                "id=" + id +
                ", receiptNo=" + receiptNo +
                ", regionId=" + regionId +
                ", prodId=" + prodId +
                ", currentStock=" + currentStock +
                ", changeStock=" + changeStock +
                "}";
    }
}
