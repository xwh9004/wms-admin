package com.wms.admin.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseVO {

    /**
     * 创建人
     */

    private String createBy;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

    /**
     * 最后更新人
     */

    private String updateBy;

    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;
}
