package com.wms.admin.vo;

import lombok.Data;

@Data
public class VendorVO {
    /**
     * 主键id
     */
    private String id;

    /**
     * 供应商编号
     */
    private String vendorNo;

    /**
     * 供应商名称
     */
    private String name;

    /**
     * 供应商类型 个人/代理
     */
    private String type;

    /**
     * 描述
     */
    private String description;

    /**
     * 供应商地址
     */
    private String address;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系人电话
     */
    private String contactPhone;

    /**
     * 状态  可用 不可用
     */
    private String status;
}
