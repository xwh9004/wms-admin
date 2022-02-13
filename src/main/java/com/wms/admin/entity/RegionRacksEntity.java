package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;

/**
 * <p>
 * 库区货架表
 * </p>
 *
 * @author Jesse
 * @since 2022-02-13 20:34:22
 */
@TableName("t_wms_region_racks")
public class RegionRacksEntity extends BaseEntity {

    /**
     * ID
     */
    @TableId("rack_no")
    private String rackNo;

    /**
     * 库区id
     */
    @TableField("region_id")
    private String regionId;

    /**
     * 存放商品类型，逗号分割多种类型
     */
    @TableField("rack_type")
    private String rackType;

    /**
     * 货位架数
     */
    @TableField("total_racks")
    private Integer totalRacks;

    /**
     * 已用货位数
     */
    @TableField("used_racks")
    private Integer usedRacks;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    public String getRackNo() {
        return rackNo;
    }

    public void setRackNo(String rackNo) {
        this.rackNo = rackNo;
    }
    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
    public String getRackType() {
        return rackType;
    }

    public void setRackType(String rackType) {
        this.rackType = rackType;
    }
    public Integer getTotalRacks() {
        return totalRacks;
    }

    public void setTotalRacks(Integer totalRacks) {
        this.totalRacks = totalRacks;
    }
    public Integer getUsedRacks() {
        return usedRacks;
    }

    public void setUsedRacks(Integer usedRacks) {
        this.usedRacks = usedRacks;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RegionRacksEntity{" +
            "rackNo=" + rackNo +
            ", regionId=" + regionId +
            ", rackType=" + rackType +
            ", totalRacks=" + totalRacks +
            ", usedRacks=" + usedRacks +
            ", description=" + description +
        "}";
    }
}
