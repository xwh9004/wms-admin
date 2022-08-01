package com.wms.admin;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

public class MyBatisPlusGenerator {

    public static void main(String[] args) {
        String author = "Jesse";
        String url = "jdbc:mysql://localhost:3306/wms_db";
        String username = "root";
        String password = "";
        String path = "D:/我的项目/wms/";
        String xmlPath="D:/我的项目/wms/mapper";

        String[] tableNames ={};
//        String[] tableNames = {"T_WMS_LEASE_CONTRACT",
//                "T_WMS_LESSEE_ADDRESS",
//                "T_WMS_LESSEE_INFO",
//        "T_WMS_MEASUREMENT_UNIT"};
        if(tableNames.length==0){
            return;
        }
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("Jesse") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .commentDate("yyyy-MM-dd HH:mm:ss")
                            .outputDir(path); // 指定输出目录

                })
                .packageConfig(builder -> {
                    builder.parent("com.wms.admin") // 设置父包名
//                            .moduleName("admin") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, xmlPath)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder
                            .addInclude(tableNames) // 设置需要生成的表名
                            .addTablePrefix("t_wms") // 设置过滤表前缀
                            .entityBuilder()
                            .superClass("com.wms.admin.entity.BaseEntity")
                            .disableSerialVersionUID()
                            .enableRemoveIsPrefix()
                            .enableTableFieldAnnotation()
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .addSuperEntityColumns("del_flag", "created_by", "created_time", "updated_by", "updated_time")
                            .formatFileName("%sEntity")
                            .build()
                            .controllerBuilder()
                            .enableRestStyle();

                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
