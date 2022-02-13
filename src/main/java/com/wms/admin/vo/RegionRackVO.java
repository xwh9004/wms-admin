package com.wms.admin.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class RegionRackVO {

    /**
     * ID
     */
    private String rackNo;

    /**
     * 库区id
     */
    private String regionId;

    /**
     * 存放商品类型，逗号分割多种类型
     */
    private String rackType;

    /**
     * 货位架数
     */
    private Integer totalRacks;

    /**
     * 已用货位数
     */
    private Integer usedRacks;

    /**
     * 描述
     */
    private String description;

}
