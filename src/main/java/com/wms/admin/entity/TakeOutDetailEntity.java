package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;

/**
 * <p>
 * 发货记录详情表
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
@TableName("t_wms_take_out_detail")
public class TakeOutDetailEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发货单号
     */
    @TableField("take_out_no")
    private String takeOutNo;

    /**
     * 货物ID
     */
    @TableField("prod_no")
    private String prodNo;

    /**
     * 货物名称
     */
    @TableField("prod_name")
    private String prodName;

    /**
     * 货物单位
     */
    @TableField("prod_unit")
    private String prodUnit;

    /**
     * 货物数量
     */
    @TableField("prod_amount")
    private Integer prodAmount;

    /**
     * 货物单价
     */
    @TableField("unit_price")
    private Integer unitPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getTakeOutNo() {
        return takeOutNo;
    }

    public void setTakeOutNo(String takeOutNo) {
        this.takeOutNo = takeOutNo;
    }
    public String getProdNo() {
        return prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }
    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
    public String getProdUnit() {
        return prodUnit;
    }

    public void setProdUnit(String prodUnit) {
        this.prodUnit = prodUnit;
    }
    public Integer getProdAmount() {
        return prodAmount;
    }

    public void setProdAmount(Integer prodAmount) {
        this.prodAmount = prodAmount;
    }
    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "TakeOutDetailEntity{" +
            "id=" + id +
            ", takeOutNo=" + takeOutNo +
            ", prodNo=" + prodNo +
            ", prodName=" + prodName +
            ", prodUnit=" + prodUnit +
            ", prodAmount=" + prodAmount +
            ", unitPrice=" + unitPrice +
        "}";
    }
}
