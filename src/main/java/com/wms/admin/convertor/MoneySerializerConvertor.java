package com.wms.admin.convertor;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.wms.admin.vo.Money;

public class MoneySerializerConvertor extends StdConverter<Money, String> {

    @Override
    public String convert(Money money) {
        return money.toString();
    }


}
