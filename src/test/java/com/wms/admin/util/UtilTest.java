package com.wms.admin.util;

import org.junit.jupiter.api.Test;

public class UtilTest {

    @Test
    public void uuid(){
        String uuid =UUIDUtil.uuid();
        System.out.println(uuid);
    }

    @Test
    public void encode(){
        System.out.println(Base64Util.encode("123456"));
    }
}
