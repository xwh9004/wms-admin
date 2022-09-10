package com.wms.admin.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: xwh90
 * @date: 2022/8/22 18:01
 * @description:
 */
@Data
public class TakeOutQueryVO implements Serializable {
    /**
     * 收货单号
     */
    private String takeOutNo;
    /**
     * 合同编号
     */
    private String contractNo;
    /**
     * 合同单位
     */
    private String contractCompany;

    private String contact;

    private String status;

    /**
     * 业务人员
     */
    private String businessUser;
    /**
     * 收货开始时间
     */
    private LocalDateTime takeOutStartTime;
    /**
     * 收货结束时间
     */
    private LocalDateTime takeOutEndTime;
}
