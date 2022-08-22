package com.wms.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wms.admin.entity.BaseEntity;

/**
 * <p>
 * 合同货物关系表
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
@TableName("t_wms_contract_prod_rel")
public class ContractProdRelEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 合同id
     */
    @TableField("contract_id")
    private Integer contractId;

    /**
     * 产品ID
     */
    @TableField("prod_id")
    private String prodId;

    /**
     * 单价/天
     */
    @TableField("lease_unit_price")
    private Integer leaseUnitPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }
    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }
    public Integer getLeaseUnitPrice() {
        return leaseUnitPrice;
    }

    public void setLeaseUnitPrice(Integer leaseUnitPrice) {
        this.leaseUnitPrice = leaseUnitPrice;
    }

    @Override
    public String toString() {
        return "ContractProdRelEntity{" +
            "id=" + id +
            ", contractId=" + contractId +
            ", prodId=" + prodId +
            ", leaseUnitPrice=" + leaseUnitPrice +
        "}";
    }
}
