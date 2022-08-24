package com.wms.admin.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: xwh90
 * @date: 2022/8/22 17:58
 * @description:
 */
@Data
public class TakeInVO implements Serializable {
    private Integer id;

    /**
     * 收货单号
     */
    private String takeInNo;

    /**
     * 收货类型
     */
    @NotBlank(message = "收货类型不能为空")
    private String takeInType;

    /**
     * 业务人员
     */
    @NotBlank(message = "业务人员不能为空")
    private String businessUser;

    /**
     * 合同编号
     */
    @NotBlank(message = "合同编号不能为空")
    private String contractNo;

    /**
     * 合同单位
     */
    @NotBlank(message = "合同单位不能为空")
    private String contractCompany;

    /**
     * 收货数量
     */
    private Integer totalAmount;

    /**
     * 收货总重量
     */
    private Integer totalWeight;

    /**
     * 货品种类数
     */
    private Integer prodTypes;

    /**
     * 卸车费
     */
    private Money unloadFee;

    /**
     * 堆码费
     */
    private Money pileFee;

    /**
     * 其他杂费
     */
    private Money othersFee;

    /**
     * 收货时间
     */
    @NotNull(message = "收货时间不能为空")
    private LocalDateTime takeInTime;

    /**
     * 描述
     */
    private String description;
    /**
     * 货物信息
     */
    @NotNull(message = "货物详情不能为空")
    private List<TakeInProdVO> prodList;
}
