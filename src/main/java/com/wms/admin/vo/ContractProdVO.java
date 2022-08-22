package com.wms.admin.vo;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * 合同货物VO
 */
@Validated
@Data
public class ContractProdVO {

    private String prodId;
    /**
     * prod_no
     */
    private String prodNo;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 价格
     */
    private Money unitPrice;
    /**
     * 产品型号
     */
    private String type;


}
