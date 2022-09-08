package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 发货记录表
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
@Data
@TableName("t_wms_take_out_record")
public class TakeOutRecordEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发货单号
     */
    @TableField("take_out_no")
    private String takeOutNo;

    /**
     * 发货类型
     */
    @TableField("take_out_type")
    private String takeOutType;

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
    /**
     * 合同单位
     */
    @TableField("contract_company")
    private String contractCompany;

    /**
     * 发货数量
     */
    @TableField("total_amount")
    private Integer totalAmount;

    /**
     * 发货总重量
     */
    @TableField("total_weight")
    private Integer totalWeight;

    /**
     * 货品种类数
     */
    @TableField("prod_types")
    private Integer prodTypes;

    /**
     * 包裹数
     */
    @TableField("package_num")
    private Integer packageNum;

    /**
     * 运费
     */
    @TableField("ship_fee")
    private Integer shipFee;

    /**
     * 上车费
     */
    @TableField("load_fee")
    private Integer loadFee;
    /**
     * 总费用
     */
    @TableField("total_fee")
    private Integer totalFee;
    /**
     * 卸车费
     */
    @TableField("unload_fee")
    private Integer unLoadFee;

    /**
     * 堆码费
     */
    @TableField("pile_fee")
    private Integer pileFee;

    /**
     * 其他杂费
     */
    @TableField("others_fee")
    private Integer othersFee;

    /**
     * 发货时间
     */
    @TableField("take_out_time")
    private LocalDateTime takeOutTime;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    @TableField("status")
    private String status;


    @Override
    public String toString() {
        return "TakeOutRecordEntity{" +
            "id=" + id +
            ", takeOutNo=" + takeOutNo +
            ", takeOutType=" + takeOutType +
            ", businessUser=" + businessUser +
            ", contractNo=" + contractNo +
            ", contact=" + contact +
            ", contractCompany=" + contractCompany +
            ", totalAmount=" + totalAmount +
            ", totalWeight=" + totalWeight +
            ", prodTypes=" + prodTypes +
            ", packageNum=" + packageNum +
            ", shipFee=" + shipFee +
            ", loadFee=" + loadFee +
            ", pileFee=" + pileFee +
            ", othersFee=" + othersFee +
            ", takeOutTime=" + takeOutTime +
            ", description=" + description +
            ", status=" + status +
        "}";
    }
}
