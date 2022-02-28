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

    public static Money valueOf(BigDecimal value){
        return new Money(value);
    }
    public Money(String value){
        this.value = ONE_HUNDRED.multiply(new BigDecimal(value)).toBigInteger().longValue();
    }

    public Long value(){
        return this.value;
    }

    public void value(Long value){
         this.value =value;
    }

    public Money(BigDecimal value){
         this.value = ONE_HUNDRED.multiply(value).toBigInteger().longValue();
    }

    @Override
    public String toString() {
        return new BigDecimal(String.valueOf(value)).divide(ONE_HUNDRED).toString();
    }

    private Long value;

    public Money multiply(BigDecimal multiplier) {
     return   new Money(multiplier.multiply(new BigDecimal(this.value))) ;
    }

    public Money add(Money adder) {
        return new Money(BigDecimal.valueOf(adder.value+this.value));
    }
}
