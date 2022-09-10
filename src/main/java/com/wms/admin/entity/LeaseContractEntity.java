package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;
import com.wms.admin.vo.Money;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 合同表
 * </p>
 *
 * @author Jesse
 * @since 2022-08-01 16:53:12
 */
@Data
@TableName("t_wms_lease_contract")
public class LeaseContractEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 合同编号
     */
    @TableField("contract_no")
    private String contractNo;

    /**
     * 业务人员
     */
    @TableField("business_user")
    private String businessUser;

    /**
     * 承租方编号
     */
    @TableField("lessee_no")
    private String lesseeNo;

    /**
     * 承租方单位
     */
    @TableField("lessee_company")
    private String lesseeCompany;

    /**
     * 承租方单位地址
     */
    @TableField("lessee_address")
    private String lesseeAddress;

    /**
     * 承租方电话
     */
    @TableField("lessee_contact")
    private String lesseeContact;

    /**
     * 承租方单位
     */
    @TableField("lessee_phone")
    private String lesseePhone;

    /**
     * 签约日期
     */
    @TableField("sign_date")
    private LocalDate signDate;

    /**
     * 生效日期
     */
    @TableField("effective_date")
    private LocalDate effectiveDate;

    /**
     * 到期日期
     */
    @TableField("expire_date")
    private LocalDate expireDate;

    /**
     * 合同押金
     */
    @TableField("deposit")
    private Money deposit;

    /**
     * 1 算头又算尾 2 算头不算尾
     */
    @TableField("bill_method")
    private String billMethod;
    /**
     * 报停收费比例
     */
    @TableField("stop_charge_fee_ratio")
    private BigDecimal stopChargeFeeRatio;
    /**
     * 违约金收费比例
     */
    @TableField("penalty_fee_ratio")
    private BigDecimal penaltyFeeRatio;
    /**
     * 袋费
     */
    @TableField("package_fee")
    private Money packageFee;
    /**
     * 每袋扣件个数
     */
    @TableField("nums_per_package")
    private Integer numsPerPackage;
    /**
     * 起租吨数
     */
    @TableField("start_rent_weight")
    private BigDecimal startRentWeight;
    /**
     * 预警欠款标准
     */
    @TableField("arrears_warring")
    private BigDecimal arrearsWarring;


    /**
     * 0 未生效 1 已生效 2 已失效
     */
    @TableField("status")
    private String status;
}
