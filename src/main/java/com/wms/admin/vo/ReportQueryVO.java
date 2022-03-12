package com.wms.admin.vo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ReportQueryVO {

    private String prodNo;
    private String prodName;
    private String applyId;
    private String regionId;
    private String fromId;
    private String toId;
    private String vendor;
    private LocalDateTime fromDate;
    private LocalDateTime endDate;

}
