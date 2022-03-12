package com.wms.admin.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;
import io.swagger.models.auth.In;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 产品库存变更记录表
 * </p>
 *
 * @author Jesse
 * @since 2022-02-26 14:22:49
 */
@Data
public class StockChangeRecordVO extends BaseEntity {
    /**
     * 单据编号
     */
    private String receiptNo;
    /**
     * 库区ID
     */
    private String regionId;
    /**
     * 库区名称
     */
    private String regionName;

    /**
     * 产品ID
     */
    private String prodId;
    /**
     * 产品编号
     */
    private String prodNo;

    /**
     * 当前库存
     */
    private Integer currentStock;

    /**
     * 变化库存
     */
    private Integer changeStock;
}
