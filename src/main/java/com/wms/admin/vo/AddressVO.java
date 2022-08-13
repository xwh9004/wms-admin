package com.wms.admin.vo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: xwh90
 * @date: 2022/8/11 15:21
 * @description: 地址VO类
 */
@Data
public class AddressVO {

    private Integer id;
    /**
     * 地址
     */
    private String address;
    /**
     * 联系人
     */
    @NotBlank(message = "联系人不能为空")
    private String contact;
    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空")
    private String phone;
    /**
     * 是否默认地址
     */
    private String isDefault;

    public boolean isDefault(){
        return StringUtils.equals(isDefault,"1");
    }


}
