package com.wms.admin.convertor;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.wms.admin.vo.Money;

import java.math.BigDecimal;

public class MoneyDeserializeConvertor extends StdConverter<String, Money> {

    @Override
    public Money convert(String value) {
        Money money = new Money(new BigDecimal(value));
        return money;
    }


}
