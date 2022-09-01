package com.wms.admin.vo;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ProductVO {
    private String id;
    /**
     * prod_no
     */
    @NotBlank(message = "货物编号不能为空")
    private String prodNo;

    /**
     * 产品名称
     */
    @NotBlank(message = "货物名称不能为空")
    private String prodName;

    /**
     * 价格
     */
    @NotNull(message = "货物租金不能为空")
    private Money unitPrice;
    /**
     * 供应商
     */
    private String vendorId;
    private String vendorName;

    /**
     * 产品型号
     */
    private String type;

    /**
     * 产品类目
     */
    @NotBlank(message = "货物大类不能为空")
    private String categoryId;
    private String categoryName;
    /**
     * 大类单位
     */
    private String categoryUnitId;
    private String categoryUnitName;
    /**
     * 货物单位ID
     */
    @NotNull(message = "货物计量单位不能为空")
    private Integer unitId;

    /**
     * 货物单位名称
     */
    private String unitName;
    /**
     * 描述
     */
    private String description;
    private String createBy;
    private String updateBy;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
