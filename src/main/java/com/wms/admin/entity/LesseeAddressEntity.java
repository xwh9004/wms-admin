package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;

/**
 * <p>
 * 承租单位地址表
 * </p>
 *
 * @author Jesse
 * @since 2022-08-01 16:53:12
 */
@TableName("t_wms_lessee_address")
public class LesseeAddressEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 承租单位ID
     */
    @TableField("lessee_id")
    private Integer lesseeId;

    /**
     * 承租单位地址
     */
    @TableField("company_address")
    private String companyAddress;

    /**
     * 承租联系人电话
     */
    @TableField("contact")
    private String contact;

    /**
     * 联系人
     */
    @TableField("phone")
    private String phone;

    /**
     * 默认地址
     */
    @TableField("is_default")
    private String isDefault;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getLesseeId() {
        return lesseeId;
    }

    public void setLesseeId(Integer lesseeId) {
        this.lesseeId = lesseeId;
    }
    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "LesseeAddressEntity{" +
            "id=" + id +
            ", lesseeId=" + lesseeId +
            ", companyAddress=" + companyAddress +
            ", contact=" + contact +
            ", phone=" + phone +
            ", isDefault=" + isDefault +
        "}";
    }
}
