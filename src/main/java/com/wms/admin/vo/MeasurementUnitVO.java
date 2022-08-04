package com.wms.admin.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *@author: Jesse
 *@date: 2022-8-01 17:45:00
 *@description: 计量单位vo
 */
@Data
public class MeasurementUnitVO {

    private Integer id;
    @NotNull(message = "符号不能为空")
    private String unitSymbol;
    @NotNull( message = "单位名称不能为空" )
    private String unitName;

}
