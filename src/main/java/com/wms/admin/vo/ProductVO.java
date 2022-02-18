package com.wms.admin.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Validated
@Data
public class ProductVO {
    private String id;

    private String prodId;
    /**
     * prod_no
     */
    private String prodNo;

    /**
     * 产品名称
     */
    private String prodName;

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
    private String categoryId;
    private String categoryName;

    /**
     * 描述
     */
    private String description;
    private String createBy;
    private String updateBy;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
