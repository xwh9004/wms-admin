package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.LeaseContractEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.ContractQueryVO;
import com.wms.admin.vo.ContractVO;

import java.util.List;

/**
 * <p>
 * 合同表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-08-01 16:53:12
 */
public interface ILeaseContractService extends IService<LeaseContractEntity> {

    List<ContractVO> contractAll();

    IPage<ContractVO> contractList(ContractQueryVO queryVO, PageParam pageParam);

    void addContract(ContractVO contractVO);

    void ineffectiveContract(Integer id);

    void deleteContract(Integer id);

    void updateContract(ContractVO contractVO);

    void effectContract(Integer id);

    ContractVO contractDetail(Integer id);
}
