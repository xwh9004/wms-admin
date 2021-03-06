package com.wms.admin.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wms.admin.convertor.MoneyDeserializeConvertor;
import com.wms.admin.convertor.MoneySerializerConvertor;

import java.math.BigDecimal;

@JsonDeserialize(converter = MoneyDeserializeConvertor.class)
@JsonSerialize(converter = MoneySerializerConvertor.class)
public class Money {

    public static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    private Long longValue;
    private BigDecimal value;

    public Money() {
    }

    public static Money valueOf(String value) {
        return valueOf(new BigDecimal(value));
    }

    public static Money valueOf(BigDecimal value) {
        return new Money(value);
    }
    public static Money longValueOf(Long value) {
       return new Money(BigDecimal.valueOf(value).divide(ONE_HUNDRED));
    }

    public Long longValue(){
        return this.longValue;
    }
    private Money(BigDecimal value) {
        BigDecimal val = new BigDecimal(value.longValue());
        val.setScale(2,BigDecimal.ROUND_FLOOR);
        this.value =val;
        this.longValue = ONE_HUNDRED.multiply(value).toBigInteger().longValue();
    }

    @Override
    public String toString() {
        return value.toString();
    }



    public Money multiply(BigDecimal multiplier) {
         return new Money(this.value.multiply(multiplier)) ;
    }

    public Money multiply(Integer multiplier) {
        return new Money(this.value.multiply(BigDecimal.valueOf(multiplier.longValue()))) ;
    }

    public Money add(Money adder) {
        return new Money(this.value.add(adder.value));
    }
}
