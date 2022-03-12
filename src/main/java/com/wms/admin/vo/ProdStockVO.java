package com.wms.admin.vo;

import lombok.Data;

@Data
public class ProdStockVO {
    private Integer id;
    private String prodNo;
    private String prodName;
    private String prodType;
    private String vendorName;
    private String regionName;
    private Integer currentStock;
    private String categoryName;
}
