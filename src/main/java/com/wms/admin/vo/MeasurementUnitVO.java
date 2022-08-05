package com.wms.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

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

    private String createBy;

    private LocalDateTime updateTime;

}
