package com.wms.admin;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
public class DBTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void dbConnectTest() throws SQLException {
        final boolean closed = dataSource.getConnection().isClosed();
        Assertions.assertEquals(false,closed);
    }

}
