package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;

/**
 * <p>
 * 承租单位表
 * </p>
 *
 * @author Jesse
 * @since 2022-08-01 16:53:12
 */
@TableName("t_wms_lessee_info")
public class LesseeInfoEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 承租单位编号
     */
    @TableField("lessee_no")
    private String lesseeNo;

    /**
     * 承租单位
     */
    @TableField("lessee_company")
    private String lesseeCompany;

    /**
     * 承租联系人
     */
    @TableField("contact")
    private String contact;

    /**
     * 承租联系人电话
     */
    @TableField("phone")
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "LesseeInfoEntity{" +
            "id=" + id +
            ", lesseeNo=" + lesseeNo +
            ", lesseeCompany=" + lesseeCompany +
            ", contact=" + contact +
            ", phone=" + phone +
        "}";
    }
}
