package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;

/**
 * <p>
 * 计量单位表
 * </p>
 *
 * @author Jesse
 * @since 2022-08-01 16:53:12
 */
@TableName("t_wms_measurement_unit")
public class MeasurementUnitEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 单位编号
     */
    @TableField("unit_symbol")
    private String unitSymbol;

    /**
     * 单位名称
     */
    @TableField("unit_name")
    private String unitName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUnitSymbol() {
        return unitSymbol;
    }

    public void setUnitSymbol(String unitSymbol) {
        this.unitSymbol = unitSymbol;
    }
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Override
    public String toString() {
        return "MeasurementUnitEntity{" +
            "id=" + id +
            ", unitSymbol=" + unitSymbol +
            ", unitName=" + unitName +
        "}";
    }
}
