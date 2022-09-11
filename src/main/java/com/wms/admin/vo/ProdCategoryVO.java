package com.wms.admin.vo;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Data
public class ProdCategoryVO extends BaseVO{

    private Integer id;

    /**
     * 大类名称
     */
    @NotNull(message = "大类名称不能为空")
    private String name;

    @NotNull(message = "大类代码不能为空")
    private String code;

    /**
     * 自然单位ID
     */
    private Integer unitId;

    /**
     * 自然单位
     */
    private Integer unitName;
    /**
     * 计费单位ID
     */
    private Integer chargeUnitId;


    /**
     * 计费单位
     */
    private Integer chargeUnitName;
    /**
     * 单位价值
     */
    private Money unitValue;
    /**
     * 单位租金
     */
    private Money unitRental;
    /**
     * 描述
     */
    private String description;

}
