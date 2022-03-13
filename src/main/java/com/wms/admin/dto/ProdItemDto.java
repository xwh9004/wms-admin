package com.wms.admin.dto;

import com.wms.admin.vo.Money;
import lombok.Data;

@Data
public class ProdItemDto {

    private String prodId;
    private String prodNo;
    private Money unitPrice;
    private Integer prodAmount;

    public ProdItemDto() {

    }

    public ProdItemDto(String prodId, Money unitPrice, Integer amount) {
        this.prodId = prodId;
        this.unitPrice = unitPrice;
        this.prodAmount = amount;
    }
}
