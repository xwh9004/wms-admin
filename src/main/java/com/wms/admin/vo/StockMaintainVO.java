package com.wms.admin.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class StockMaintainVO {
    private Integer id;

    /**
     * 产品ID
     */
    private String prodId;

    /**
     * 库存上限
     */
    private Integer upBound;

    /**
     * 库存下限
     */
    private Integer downBound;

}
