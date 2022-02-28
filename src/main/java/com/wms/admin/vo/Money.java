package com.wms.admin.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wms.admin.convertor.MoneyDeserializeConvertor;
import com.wms.admin.convertor.MoneySerializerConvertor;

import java.math.BigDecimal;
@JsonDeserialize(converter = MoneyDeserializeConvertor.class)
@JsonSerialize(converter = MoneySerializerConvertor.class)
public class Money {

    public static final BigDecimal ONE_HUNDRED =BigDecimal.valueOf(100);
    public Money(){

    }
    public Money(String value){
        this.value = ONE_HUNDRED.multiply(new BigDecimal(value)).toBigInteger().intValue();
    }

    public Integer value(){
        return this.value;
    }

    public void value(Integer value){
         this.value =value;
    }

    public Money(BigDecimal value){
         this.value = ONE_HUNDRED.multiply(value).toBigInteger().intValue();
    }

    @Override
    public String toString() {
        return new BigDecimal(String.valueOf(value)).divide(ONE_HUNDRED).toString();
    }

    private Integer value;
}
