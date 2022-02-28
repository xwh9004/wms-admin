package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;

/**
 * <p>
 * 库存维护记录表
 * </p>
 *
 * @author Jesse
 * @since 2022-02-27 20:57:49
 */
@TableName("t_wms_stock_maintain")
public class StockMaintainEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品ID
     */
    @TableField("prod_id")
    private String prodId;

    /**
     * 库存上限
     */
    @TableField("up_bound")
    private Integer upBound;

    /**
     * 库存下限
     */
    @TableField("down_bound")
    private Integer downBound;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }
    public Integer getUpBound() {
        return upBound;
    }

    public void setUpBound(Integer upBound) {
        this.upBound = upBound;
    }
    public Integer getDownBound() {
        return downBound;
    }

    public void setDownBound(Integer downBound) {
        this.downBound = downBound;
    }

    @Override
    public String toString() {
        return "StockMaintainEntity{" +
            "id=" + id +
            ", prodId=" + prodId +
            ", upBound=" + upBound +
            ", downBound=" + downBound +
        "}";
    }
}
