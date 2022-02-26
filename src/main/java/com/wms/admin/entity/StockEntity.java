package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;

/**
 * <p>
 * 库存记录表
 * </p>
 *
 * @author Jesse
 * @since 2022-02-26 14:22:49
 */
@TableName("t_wms_stock")
public class StockEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "StockEntity{" +
            "id=" + id +
            ", regionId=" + regionId +
            ", prodId=" + prodId +
            ", currentStock=" + currentStock +
        "}";
    }
}
