package com.wms.admin;

import org.junit.jupiter.api.Test;
import org.springframework.util.AntPathMatcher;

/**
 * @author: xwh90
 * @date: 2022/9/12 20:54
 * @description:
 */
public class PathTest {

    @Test
    public void test(){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        System.out.println(antPathMatcher.match("/take-in/update/{id}","/take-in/update/1"));
    }
}
