package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;
import java.time.LocalDateTime;

/**
 * <p>
 * 发货记录表
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getTakeOutNo() {
        return takeOutNo;
    }

    public void setTakeOutNo(String takeOutNo) {
        this.takeOutNo = takeOutNo;
    }
    public String getTakeOutType() {
        return takeOutType;
    }

    public void setTakeOutType(String takeOutType) {
        this.takeOutType = takeOutType;
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
    public Integer getPackageNum() {
        return packageNum;
    }

    public void setPackageNum(Integer packageNum) {
        this.packageNum = packageNum;
    }
    public Integer getShipFee() {
        return shipFee;
    }

    public void setShipFee(Integer shipFee) {
        this.shipFee = shipFee;
    }
    public Integer getLoadFee() {
        return loadFee;
    }

    public void setLoadFee(Integer loadFee) {
        this.loadFee = loadFee;
    }
    public Integer getPileFee() {
        return pileFee;
    }

    public void setPileFee(Integer pileFee) {
        this.pileFee = pileFee;
    }
    public Integer getOthersFee() {
        return othersFee;
    }

    public void setOthersFee(Integer othersFee) {
        this.othersFee = othersFee;
    }
    public LocalDateTime getTakeOutTime() {
        return takeOutTime;
    }

    public void setTakeOutTime(LocalDateTime takeOutTime) {
        this.takeOutTime = takeOutTime;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TakeOutRecordEntity{" +
            "id=" + id +
            ", takeOutNo=" + takeOutNo +
            ", takeOutType=" + takeOutType +
            ", businessUser=" + businessUser +
            ", contractNo=" + contractNo +
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
        "}";
    }
}
