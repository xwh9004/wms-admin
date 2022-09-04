package com.wms.admin.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: xwh90
 * @date: 2022/8/22 18:00
 * @description:
 */
@Data
public class TakeOutVO implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发货单号
     */
    private String takeOutNo;

    /**
     * 发货类型
     */
    private String takeOutType;

    /**
     * 业务人员
     */
    private String businessUser;

    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 合同单位
     */
    private String contractCompany;

    /**
     * 发货数量
     */
    private BigDecimal totalAmount;

    /**
     * 发货总重量
     */
    private Integer totalWeight;

    /**
     * 货品种类数
     */
    private Integer prodTypes;

    /**
     * 包裹数
     */
    private Integer packageNum;

    /**
     * 运费
     */
    private Money shipFee;

    /**
     * 上车费
     */
    private Money loadFee;

    /**
     * 堆码费
     */
    private Money pileFee;

    /**
     * 其他杂费
     */
    private Money othersFee;

    /**
     * 发货时间
     */
    private LocalDateTime takeOutTime;

    /**
     * 描述
     */
    private String description;

    private List<TakeOutProdVO> prodList;

    private String status;
}
