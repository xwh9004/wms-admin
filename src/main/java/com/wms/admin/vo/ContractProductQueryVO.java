package com.wms.admin.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author: xwh90
 * @date: 2022/9/4 9:28
 * @description: 合同内货物信息查询VO类型
 */
@Data
public class ContractProductQueryVO implements Serializable {
    /**
     * 合同编号
     */
    @NotNull(message = "合同ID不能为空")
    private Integer contractId;

    private String prodNo;

    private String prodName;

}
