package com.wms.admin.vo;

import lombok.Data;

@Data
public class MonthStatisticVO {
    private String month;
    private String receiptType;
    private Integer totalAmount;
}
