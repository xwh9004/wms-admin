package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;
import com.wms.admin.vo.Money;
import lombok.Data;

/**
 * <p>
 * 收货记录详情表
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
@Data
@TableName("t_wms_take_in_detail")
public class TakeInDetailEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 收货单号
     */
    @TableField("take_in_no")
    private String takeInNo;

    /**
     * 货物ID
     */
    @TableField("prod_no")
    private String prodNo;

    /**
     * 货物名称
     */
    @TableField("prod_name")
    private String prodName;

    /**
     * 货物单位
     */
    @TableField("prod_unit")
    private String prodUnit;

    /**
     * 货物数量
     */
    @TableField("prod_amount")
    private Integer prodAmount;

    @TableField("status")
    private String status;

    /**
     * 货物单价
     */
    @TableField("unit_price")
    private Money unitPrice;

    @Override
    public String toString() {
        return "TakeInDetailEntity{" +
            "id=" + id +
            ", takeInNo=" + takeInNo +
            ", prodNo=" + prodNo +
            ", prodName=" + prodName +
            ", prodUnit=" + prodUnit +
            ", prodAmount=" + prodAmount +
            ", unitPrice=" + unitPrice +
            ", status=" + status +
        "}";
    }
}
