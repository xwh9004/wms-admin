package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;
import java.time.LocalDateTime;

/**
 * <p>
 * 合同表
 * </p>
 *
 * @author Jesse
 * @since 2022-08-01 16:53:12
 */
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
    @TableField("lessee_addr")
    private String lesseeAddr;

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
    private LocalDateTime signDate;

    /**
     * 生效日期
     */
    @TableField("effective_date")
    private LocalDateTime effectiveDate;

    /**
     * 到期日期
     */
    @TableField("expire_date")
    private LocalDateTime expireDate;

    /**
     * 合同押金
     */
    @TableField("deposit")
    private Integer deposit;

    /**
     * 1 算头又算尾 2 算头不算尾
     */
    @TableField("bill_method")
    private String billMethod;

    /**
     * 默认地址
     */
    @TableField("effective")
    private String effective;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }
    public String getBusinessUser() {
        return businessUser;
    }

    public void setBusinessUser(String businessUser) {
        this.businessUser = businessUser;
    }
    public String getLesseeNo() {
        return lesseeNo;
    }

    public void setLesseeNo(String lesseeNo) {
        this.lesseeNo = lesseeNo;
    }
    public String getLesseeCompany() {
        return lesseeCompany;
    }

    public void setLesseeCompany(String lesseeCompany) {
        this.lesseeCompany = lesseeCompany;
    }
    public String getLesseeAddr() {
        return lesseeAddr;
    }

    public void setLesseeAddr(String lesseeAddr) {
        this.lesseeAddr = lesseeAddr;
    }
    public String getLesseeContact() {
        return lesseeContact;
    }

    public void setLesseeContact(String lesseeContact) {
        this.lesseeContact = lesseeContact;
    }
    public String getLesseePhone() {
        return lesseePhone;
    }

    public void setLesseePhone(String lesseePhone) {
        this.lesseePhone = lesseePhone;
    }
    public LocalDateTime getSignDate() {
        return signDate;
    }

    public void setSignDate(LocalDateTime signDate) {
        this.signDate = signDate;
    }
    public LocalDateTime getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }
    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }
    public String getBillMethod() {
        return billMethod;
    }

    public void setBillMethod(String billMethod) {
        this.billMethod = billMethod;
    }
    public String getEffective() {
        return effective;
    }

    public void setEffective(String effective) {
        this.effective = effective;
    }

    @Override
    public String toString() {
        return "LeaseContractEntity{" +
            "id=" + id +
            ", contractNo=" + contractNo +
            ", businessUser=" + businessUser +
            ", lesseeNo=" + lesseeNo +
            ", lesseeCompany=" + lesseeCompany +
            ", lesseeAddr=" + lesseeAddr +
            ", lesseeContact=" + lesseeContact +
            ", lesseePhone=" + lesseePhone +
            ", signDate=" + signDate +
            ", effectiveDate=" + effectiveDate +
            ", expireDate=" + expireDate +
            ", deposit=" + deposit +
            ", billMethod=" + billMethod +
            ", effective=" + effective +
        "}";
    }
}
