package com.wms.admin.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    @NotBlank(message = "客户编号不能为空")
    private String lesseeNo;

    /**
     * 承租方单位
     */
    @NotBlank(message = "客户单位名称不能为空")
    private String lesseeCompany;

    /**
     * 承租方单位地址
     */
    private String lesseeAddress;

    /**
     * 承租方联系人
     */
    @NotBlank(message = "客户联系人不能为空")
    private String lesseeContact;

    /**
     * 承租方联系电话
     */
    private String lesseePhone;

    /**
     * 签约日期
     */
    @NotNull(message = "签约不能为空")
    private LocalDate signDate;

    /**
     * 生效日期
     */
    @NotNull(message = "生效日期不能为空")
    private LocalDate effectiveDate;

    /**
     * 到期日期
     */
    private LocalDate expireDate;

    /**
     * 合同押金
     */
    @NotNull(message = "押金不能为空")
    private Money deposit;

    /**
     * 1 算头又算尾 2 算头不算尾
     */
    @NotNull(message = "结算方式不能为空")
    private String billMethod;

    /**
     * 报停收费比例
     */
    private BigDecimal stopChargeFeeRatio;
    /**
     * 违约金收费比例
     */
    private String penaltyFeeRatio;
    /**
     * 袋费
     */
    private Money packageFee;
    /**
     * 每袋扣件个数
     */
    private Integer numsPerPackage;
    /**
     * 起租吨数
     */
    private BigDecimal startRentWeight;
    /**
     * 预警欠款标准
     */
    private Money arrearsWarring;

    private String status;

    private LocalDateTime updateTime;

    /**
     * 是否有效
     */
    private String isEffective;
    @NotNull(message = "合同明细不能为空")
    private List<ContractProdVO> list;

}
