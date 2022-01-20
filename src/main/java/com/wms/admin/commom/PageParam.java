package com.wms.admin.commom;


import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class PageParam {

    private Integer pageIndex;

    private Integer pageSize;
}
