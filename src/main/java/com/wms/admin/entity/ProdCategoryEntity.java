package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;
import com.wms.admin.vo.Money;
import lombok.Data;

/**
 * <p>
 * 货物大类表
 * </p>
 *
 * @author Jesse
 * @since 2022-02-14 14:43:16
 */
@Data
@TableName("t_wms_prod_category")
public class ProdCategoryEntity extends BaseEntity {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 大类名称
     */
    @TableField("name")
    private String name;
    /**
     * 大类编号
     */
    @TableField("code")
    private String code;
    /**
     * 自然单位ID
     */
    @TableField("unit_id")
    private Integer unitId;

    /**
     * 计费单位ID
     */
    @TableField("charge_unit_id")
    private Integer chargeUnitId;

    /**
     * 单位价值
     */
    @TableField("unit_value")
    private Money unitValue;

    /**
     * 单位租金
     */
    @TableField("unit_rental")
    private Money unitRental;
    /**
     * 描述
     */
    @TableField("description")
    private String description;

}
