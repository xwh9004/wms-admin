package com.wms.admin.commom;


import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

@Data
public class PageParam {

    @Min(value = 1,message = "页码不能小于0")
    private Integer page =1;

    private Integer limit =10;
}
