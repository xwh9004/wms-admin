package com.wms.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@MapperScan("com.wms.admin.mapper")
@SpringBootApplication
public class WmsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmsAdminApplication.class, args);
    }

}
