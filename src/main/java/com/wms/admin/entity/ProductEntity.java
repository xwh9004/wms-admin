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
 * @since 2022-02-13 20:34:22
 */
@TableName("t_wms_product")
public class ProductEntity extends BaseEntity {

    /**
     * prod_no
     */
    @TableId("prod_no")
    private String prodNo;

    /**
     * 产品名称
     */
    @TableField("product_name")
    private String productName;

    /**
     * 供应商
     */
    @TableField("vendor")
    private String vendor;

    /**
     * 产品型号
     */
    @TableField("type")
    private String type;

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

    public String getProdNo() {
        return prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
            "prodNo=" + prodNo +
            ", productName=" + productName +
            ", vendor=" + vendor +
            ", type=" + type +
            ", categoryId=" + categoryId +
            ", description=" + description +
        "}";
    }
}
