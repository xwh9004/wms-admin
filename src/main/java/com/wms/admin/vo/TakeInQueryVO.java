package com.wms.admin.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: xwh90
 * @date: 2022/8/22 17:57
 * @description:
 */
@Data
public class TakeInQueryVO implements Serializable {
    /**
     * 收货单号
     */
    private String takeInNo;
    /**
     * 合同编号
     */
    private String contractNo;
    /**
     * 合同单位
     */
    private String contractCompany;
    /**
     * 收货开始时间
     */
    private LocalDateTime takeInStartTime;
    /**
     * 收货结束时间
     */
    private LocalDateTime takeInEndTime;
}
