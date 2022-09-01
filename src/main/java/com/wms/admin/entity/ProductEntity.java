package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.convertor.MoneyTypeHandler;
import com.wms.admin.entity.BaseEntity;
import com.wms.admin.vo.Money;
import lombok.Data;

/**
 * <p>
 * 货物表
 * </p>
 *
 * @author Jesse
 * @since 2022-02-14 14:43:16
 */
@Data
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
     * 产品单位
     */
    @TableField(value = "unit_id")
    private Integer unitId;
    /**
     * 产品单价
     */
    @TableField(value = "unit_price",typeHandler = MoneyTypeHandler.class)
    private Money unitPrice;

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
}
