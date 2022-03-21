package com.wms.admin.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Validated
@Data
public class ProdCategoryVO extends BaseVO{

    private String id;

    /**
     * 大类名称
     */
    @NotNull(message = "大类名称不能为空")
    private String name;

    @NotNull(message = "大类代码不能为空")
    private String code;

    /**
     * 描述
     */
    private String description;
}
