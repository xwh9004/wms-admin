package com.wms.admin.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: xwh90
 * @date: 2022/8/16 15:17
 * @description: 合同信息VO类
 */
@Data
public class ContractVO implements Serializable {
    private Integer id;
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
     * 承租方单位
     */
    private String lesseeCompany;

    /**
     * 承租方单位地址
     */
    private String lesseeAddr;

    /**
     * 承租方电话
     */
    private String lesseeContact;

    /**
     * 承租方单位
     */
    private String lesseePhone;

    /**
     * 签约日期
     */
    private LocalDateTime signDate;

    /**
     * 生效日期
     */
    private LocalDateTime effectiveDate;

    /**
     * 到期日期
     */
    private LocalDateTime expireDate;

    /**
     * 合同押金
     */
    private Integer deposit;

    /**
     * 1 算头又算尾 2 算头不算尾
     */
    private String billMethod;

    /**
     * 默认地址
     */
    private String effective;

}
