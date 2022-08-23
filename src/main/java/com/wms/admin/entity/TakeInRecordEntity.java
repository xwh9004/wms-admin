package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;
import com.wms.admin.vo.Money;

import java.time.LocalDateTime;

/**
 * <p>
 * 收货记录表
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
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

    /**
     * 合同单位
     */
    @TableField("contract_company")
    private String contractCompany;

    /**
     * 收货数量
     */
    @TableField("total_amount")
    private Integer totalAmount;

    /**
     * 收货总重量
     */
    @TableField("total_weight")
    private Integer totalWeight;

    /**
     * 货品种类数
     */
    @TableField("prod_types")
    private Integer prodTypes;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getTakeInNo() {
        return takeInNo;
    }

    public void setTakeInNo(String takeInNo) {
        this.takeInNo = takeInNo;
    }
    public String getTakeInType() {
        return takeInType;
    }

    public void setTakeInType(String takeInType) {
        this.takeInType = takeInType;
    }
    public String getBusinessUser() {
        return businessUser;
    }

    public void setBusinessUser(String businessUser) {
        this.businessUser = businessUser;
    }
    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }
    public String getContractCompany() {
        return contractCompany;
    }

    public void setContractCompany(String contractCompany) {
        this.contractCompany = contractCompany;
    }
    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }
    public Integer getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Integer totalWeight) {
        this.totalWeight = totalWeight;
    }
    public Integer getProdTypes() {
        return prodTypes;
    }

    public void setProdTypes(Integer prodTypes) {
        this.prodTypes = prodTypes;
    }

    public Money getUnloadFee() {
        return unloadFee;
    }

    public void setUnloadFee(Money unloadFee) {
        this.unloadFee = unloadFee;
    }

    public Money getPileFee() {
        return pileFee;
    }

    public void setPileFee(Money pileFee) {
        this.pileFee = pileFee;
    }

    public Money getOthersFee() {
        return othersFee;
    }

    public void setOthersFee(Money othersFee) {
        this.othersFee = othersFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTakeInTime() {
        return takeInTime;
    }

    public void setTakeInTime(LocalDateTime takeInTime) {
        this.takeInTime = takeInTime;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TakeInRecordEntity{" +
            "id=" + id +
            ", takeInNo=" + takeInNo +
            ", takeInType=" + takeInType +
            ", businessUser=" + businessUser +
            ", contractNo=" + contractNo +
            ", contractCompany=" + contractCompany +
            ", totalAmount=" + totalAmount +
            ", totalWeight=" + totalWeight +
            ", prodTypes=" + prodTypes +
            ", unloadFee=" + unloadFee +
            ", pileFee=" + pileFee +
            ", othersFee=" + othersFee +
            ", takeInTime=" + takeInTime +
            ", description=" + description +
            ", status=" + status +
        "}";
    }
}
