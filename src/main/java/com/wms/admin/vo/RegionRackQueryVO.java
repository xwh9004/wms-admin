package com.wms.admin.vo;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class RegionRackQueryVO {

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




}
