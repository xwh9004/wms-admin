package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;

/**
 * <p>
 * 货物表
 * </p>
 *
 * @author Jesse
 * @since 2022-02-14 14:43:16
 */
@TableName("t_wms_product")
public class ProductEntity extends BaseEntity {
    /**
     * id
     */
    @TableId("id")
    private String id;
    /**
     * prod_no
     */
    @TableField("prod_no")
    private String prodNo;

    /**
     * 产品名称
     */
    @TableField("prod_name")
    private String prodName;

    /**
     * 供应商
     */
    @TableField("vendor_id")
    private String vendorId;

    /**
     * 产品型号
     */
    @TableField("type")
    private String type;

    /**
     * 产品型号
     */
    @TableField("unit_price")
    private Integer unitPrice;

    /**
     * 产品类目
     */
    @TableField("category_id")
    private String categoryId;

    /**
     * 描述
     */
    @TableField("description")
    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
            "id=" + id +
            "prodNo=" + prodNo +
            ", prodName=" + prodName +
            ", vendorId=" + vendorId +
            ", type=" + type +
            ", unitPrice=" + unitPrice +
            ", categoryId=" + categoryId +
            ", description=" + description +
        "}";
    }
}
