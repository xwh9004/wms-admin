package com.wms.admin.dto;

import lombok.Data;

@Data
public class MonthStatisticDto {
    private String month;
    private String receiptType;
    private Integer totalAmount;
}
