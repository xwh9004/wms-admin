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
public class LesseeInfoVO implements Serializable {

    private Integer id;
    /**
     * 承租单位编号
     */
    @NotBlank(message = "承租单位编号不能为空")
    private String lesseeNo;
    /**
     * 承租单位
     */
    @NotBlank(message = "承租单位不能为空")
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

    private String createBy;
    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 地址
     */
    private List<AddressVO> list;

}
