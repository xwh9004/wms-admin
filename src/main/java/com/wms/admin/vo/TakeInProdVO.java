package com.wms.admin.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: xwh90
 * @date: 2022/8/23 11:28
 * @description: 收货货物信息
 */
@Data
public class TakeInProdVO implements Serializable {
    /**
     * 货物ID
     */
    @NotBlank(message = "货物ID不能为空")
    private String prodId;
    /**
     * 货物编号
     */
    @NotBlank(message = "货物编号不能为空")
    private String prodNo;
    private String type;

    /**
     * 货物名称
     */
    @NotBlank(message = "货物名称不能为空")
    private String prodName;

    /**
     * 货物单位
     */
    @NotBlank(message = "货物单位不能为空")
    private String prodUnit;

    private String unitName;

    private BigDecimal quantity;

    /**
     * 货物数量
     */
    @NotNull(message = "货物数量不能为空")
    private Integer prodAmount;

    /**
     * 货物单价
     */
    @NotNull(message = "货物单价不能为空")
    private Money unitPrice;

    private String categoryId;
    private String categoryName;
    /**
     * 大类单位
     */
    private String categoryUnitId;
    private String categoryUnitName;

}
