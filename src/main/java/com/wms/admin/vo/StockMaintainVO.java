package com.wms.admin.vo;

import lombok.Data;

@Data
public class StockMaintainVO extends BaseVO {
    private Integer id;

    /**
     * 产品ID
     */
    private String prodId;
    /**
     * 产品编号
     */
    private String prodNo;

    /**
     * 库存上限
     */
    private Integer upBound;

    /**
     * 库存下限
     */
    private Integer downBound;

    private String prodName;
    private String prodType;

    private String vendorName;


}
