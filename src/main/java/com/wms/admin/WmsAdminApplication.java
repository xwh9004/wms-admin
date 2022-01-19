package com.wms.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class WmsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmsAdminApplication.class, args);
    }

}
