package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;

/**
 * <p>
 * 供应商表
 * </p>
 *
 * @author Jesse
 * @since 2022-02-14 14:43:16
 */
@TableName("t_wms_vendor")
public class VendorEntity extends BaseEntity {

    /**
     * 主键id
     */
    @TableId("id")
    private String id;

    /**
     * 供应商编号
     */
    @TableField("vendor_no")
    private String vendorNo;

    /**
     * 供应商名称
     */
    @TableField("name")
    private String name;

    /**
     * 供应商类型 个人/代理
     */
    @TableField("type")
    private String type;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 供应商地址
     */
    @TableField("address")
    private String address;

    /**
     * 联系人
     */
    @TableField("contact")
    private String contact;

    /**
     * 联系人电话
     */
    @TableField("contact_phone")
    private String contactPhone;

    /**
     * 状态  可用 不可用
     */
    @TableField("status")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getVendorNo() {
        return vendorNo;
    }

    public void setVendorNo(String vendorNo) {
        this.vendorNo = vendorNo;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "VendorEntity{" +
            "id=" + id +
            ", vendorNo=" + vendorNo +
            ", name=" + name +
            ", type=" + type +
            ", description=" + description +
            ", address=" + address +
            ", contact=" + contact +
            ", contactPhone=" + contactPhone +
            ", status=" + status +
        "}";
    }
}
