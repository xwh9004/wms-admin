package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;

/**
 * <p>
 * 仓库库区表
 * </p>
 *
 * @author Jesse
 * @since 2022-02-14 14:43:16
 */
@TableName("t_wms_storages_region")
public class StoragesRegionEntity extends BaseEntity {

    /**
     * 仓库ID
     */
    @TableId("id")
    private String id;

    /**
     * 仓库编号
     */
    @TableField("region_no")
    private String regionNo;

    /**
     * 仓库名称
     */
    @TableField("region_name")
    private String regionName;

    /**
     * 仓库类型 1 一级库 2 二级库
     */
    @TableField("region_type")
    private String regionType;

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
    public String getRegionNo() {
        return regionNo;
    }

    public void setRegionNo(String regionNo) {
        this.regionNo = regionNo;
    }
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
    public String getRegionType() {
        return regionType;
    }

    public void setRegionType(String regionType) {
        this.regionType = regionType;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "StoragesRegionEntity{" +
            "id=" + id +
            ", regionNo=" + regionNo +
            ", regionName=" + regionName +
            ", regionType=" + regionType +
            ", description=" + description +
        "}";
    }
}
