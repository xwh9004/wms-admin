package com.wms.admin.service;

import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.LeaseContractEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.ContractVO;

/**
 * <p>
 * 合同表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-08-01 16:53:12
 */
public interface ILeaseContractService extends IService<LeaseContractEntity> {

    void contractAll(ContractVO contractVO, PageParam pageParam);

    void contractList(ContractVO contractVO, PageParam pageParam);

    void addContract(ContractVO contractVO);

    void deleteContract(Integer id);
}
