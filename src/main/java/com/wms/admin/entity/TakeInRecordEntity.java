package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.vo.Money;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 收货记录表
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
@Data
@TableName("t_wms_take_in_record")
public class TakeInRecordEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 收货单号
     */
    @TableField("take_in_no")
    private String takeInNo;

    /**
     * 收货类型
     */
    @TableField("take_in_type")
    private String takeInType;

    /**
     * 业务人员
     */
    @TableField("business_user")
    private String businessUser;

    /**
     * 合同编号
     */
    @TableField("contract_no")
    private String contractNo;

    @TableField("contact")
    private String contact;


    @TableField("phone")
    private String phone;
    /**
     * 合同单位
     */
    @TableField("contract_company")
    private String contractCompany;

    /**
     * 收货单总费用
     */
    @TableField("total_fee")
    private Money totalFee;

    /**
     * 收货总重量
     */
    @TableField("total_weight")
    private BigDecimal totalWeight;

    /**
     * 货品种类数
     */
    @TableField("prod_types")
    private Integer prodTypes;

    /**
     * 上车费
     */
    @TableField("ship_fee")
    private Money shipFee;
    /**
     * 上车费
     */
    @TableField("load_fee")
    private Money loadFee;
    /**
     * 卸车费
     */
    @TableField("unload_fee")
    private Money unloadFee;

    /**
     * 堆码费
     */
    @TableField("pile_fee")
    private Money pileFee;

    /**
     * 其他杂费
     */
    @TableField("others_fee")
    private Money othersFee;

    /**
     * 收货时间
     */
    @TableField("take_in_time")
    private LocalDateTime takeInTime;
    /**
     * 0 未入库 1 已签收
     */
    @TableField("status")
    private String status;
    /**
     * 描述
     */
    @TableField("description")
    private String description;

    @Override
    public String toString() {
        return "TakeInRecordEntity{" +
                "id=" + id +
                ", takeInNo=" + takeInNo +
                ", takeInType=" + takeInType +
                ", businessUser=" + businessUser +
                ", contractNo=" + contractNo +
                ", contact=" + contact +
                ", contractCompany=" + contractCompany +
                ", totalWeight=" + totalWeight +
                ", prodTypes=" + prodTypes +
                ", unloadFee=" + unloadFee +
                ", pileFee=" + pileFee +
                ", loadFee=" + loadFee +
                ", shipFee=" + shipFee +
                ", othersFee=" + othersFee +
                ", totalFee=" + totalFee +
                ", takeInTime=" + takeInTime +
                ", description=" + description +
                ", status=" + status +
                "}";
    }
}
