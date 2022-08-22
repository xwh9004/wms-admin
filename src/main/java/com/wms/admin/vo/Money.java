package com.wms.admin.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wms.admin.convertor.MoneyDeserializeConvertor;
import com.wms.admin.convertor.MoneySerializerConvertor;

import java.math.BigDecimal;

@JsonDeserialize(converter = MoneyDeserializeConvertor.class)
@JsonSerialize(converter = MoneySerializerConvertor.class)
public class Money {

//    public static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);
//    public static final BigDecimal ONE_THOUSAND = BigDecimal.valueOf(1000);
    public static final BigDecimal TEN_THOUSAND = BigDecimal.valueOf(10000);

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
       return new Money(BigDecimal.valueOf(value).divide(TEN_THOUSAND));
    }

    public Long longValue(){
        return this.longValue;
    }
    private Money(BigDecimal value) {
        value = value.setScale(4,BigDecimal.ROUND_FLOOR);
        //厘 小数点4位
        this.value =value;
        this.longValue = TEN_THOUSAND.multiply(value).longValue();
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
