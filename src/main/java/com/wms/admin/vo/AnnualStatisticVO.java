package com.wms.admin.vo;

import lombok.Data;

@Data
public class AnnualStatisticVO {

    private Integer storageInAmount;
    private Integer storageOutAmount;
    private Integer storageShiftAmount;
    private Integer discardAmount;

}
