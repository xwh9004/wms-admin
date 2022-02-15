package com.wms.admin.vo;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class ProductQueryVO {

    /**
     * prod_no
     */
    private String prodNo;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 供应商
     */
    private String vendorId;


}
