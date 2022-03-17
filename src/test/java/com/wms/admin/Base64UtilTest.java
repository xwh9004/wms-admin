package com.wms.admin;

import com.wms.admin.util.Base64Util;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Base64UtilTest {

    @Test
    public void test() {
        String encrypt = Base64Util.encode("124");
        System.out.println(Base64Util.decode(encrypt));
        List<String> list = new ArrayList<>();
        list.forEach(System.out::println);
    }
}
