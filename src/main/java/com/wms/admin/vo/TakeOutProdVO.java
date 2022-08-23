package com.wms.admin.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xwh90
 * @date: 2022/8/23 11:28
 * @description: 收货货物信息
 */
@Data
public class TakeOutProdVO implements Serializable {
    /**
     * 货物ID
     */
    private String prodId;
    /**
     * 货物编号
     */
    private String prodNo;

    /**
     * 货物名称
     */
    private String prodName;

    /**
     * 货物单位
     */
    private String prodUnit;

    /**
     * 货物数量
     */
    private Integer prodAmount;

    /**
     * 货物单价
     */
    private Money unitPrice;

}
