package com.wms.admin.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: xwh90
 * @date: 2022/8/10 15:17
 * @description: 承租单位信息
 */
@Data
public class LesseeInfoAndAddressesVO implements Serializable {

    /**
     * 承租单位编号
     */
    private String lesseeNo;
    /**
     * 承租单位
     */
    private String lesseeCompany;

    /**
     * 承租联系人
     *
     * @return
     */
    private String contact;

    /**
     *承租联系人电话
     *
     */
    private String phone;

    private String address;

}
