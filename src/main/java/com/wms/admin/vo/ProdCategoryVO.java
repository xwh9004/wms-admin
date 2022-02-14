package com.wms.admin.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Validated
@Data
public class ProdCategoryVO {

    private String id;

    /**
     * 大类名称
     */

    private String name;


    private String code;

    /**
     * 描述
     */
    private String description;


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
