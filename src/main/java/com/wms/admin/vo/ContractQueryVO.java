package com.wms.admin.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: xwh90
 * @date: 2022/8/16 15:17
 * @description: 合同信息查询VO类型
 */
@Data
public class ContractQueryVO implements Serializable {
    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 业务人员
     */
    private String businessUser;

    /**
     * 承租方编号
     */
    private String lesseeNo;

    /**
     * 承租方联系人
     */
    private String lesseeContact;

    /**
     * 承租方联系电话
     */
    private String lesseePhone;


    /**
     * 1 算头又算尾 2 算头不算尾
     */
    private String billMethod;

    /**
     * 状态
     */
    private String status;

}
