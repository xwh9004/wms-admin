package com.wms.admin.vo;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractProdVO that = (ContractProdVO) o;
        return Objects.equals(prodId, that.prodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prodId, prodNo, prodName, unitPrice, type);
    }
}
