package com.wms.admin;

import com.wms.admin.util.Base64Util;
import org.junit.jupiter.api.Test;

public class Base64UtilTest {

    @Test
    public void test() {
        String encrypt = Base64Util.encode("124");
        System.out.println(Base64Util.decode(encrypt));

    }
}
