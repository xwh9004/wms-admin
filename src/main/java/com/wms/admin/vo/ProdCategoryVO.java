package com.wms.admin.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class ProdCategoryVO {

    private String id;

    /**
     * 大类名称
     */

    private String name;



    /**
     * 描述
     */
    private String description;
}
