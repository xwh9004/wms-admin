package com.wms.admin.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.wms.admin.auth.UserInfoContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充系统字段
 */
@Slf4j
@Component
public class MyBatisMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createBy", String.class, UserInfoContext.getUsername()); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "updateBy", String.class, UserInfoContext.getUsername()); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName( "updateTime", LocalDateTime.now(),metaObject);
        this.setFieldValByName( "updateBy", UserInfoContext.getUsername(),metaObject);
    }

}
