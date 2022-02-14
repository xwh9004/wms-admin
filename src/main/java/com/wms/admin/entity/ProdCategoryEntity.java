package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;

/**
 * <p>
 * 货物大类表
 * </p>
 *
 * @author Jesse
 * @since 2022-02-14 14:43:16
 */
@TableName("t_wms_prod_category")
public class ProdCategoryEntity extends BaseEntity {

    /**
     * ID
     */
    @TableId("id")
    private String id;

    /**
     * 大类名称
     */
    @TableField("name")
    private String name;

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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProdCategoryEntity{" +
            "id=" + id +
            ", name=" + name +
            ", description=" + description +
        "}";
    }
}
