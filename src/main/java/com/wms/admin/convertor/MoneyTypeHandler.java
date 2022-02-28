package com.wms.admin.convertor;

import com.wms.admin.vo.Money;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoneyTypeHandler  extends BaseTypeHandler<Money> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Money o, JdbcType jdbcType) throws SQLException {
        preparedStatement.setLong(i,o.value());
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        long value = resultSet.getLong(columnName);
        Money money = new Money();
        money.value(value);
        return money;
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, int i) throws SQLException {
        long value = resultSet.getLong(i);
        Money money = new Money();
        money.value(value);
        return money;
    }

    @Override
    public Money getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        long value = callableStatement.getLong(i);
        Money money = new Money();
        money.value(value);
        return money;
    }
}
