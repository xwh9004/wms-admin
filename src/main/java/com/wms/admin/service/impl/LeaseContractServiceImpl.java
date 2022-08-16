package com.wms.admin.service.impl;

import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.LeaseContractEntity;
import com.wms.admin.mapper.LeaseContractMapper;
import com.wms.admin.service.ILeaseContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.vo.ContractVO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 合同表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-08-01 16:53:12
 */
@Service
public class LeaseContractServiceImpl extends ServiceImpl<LeaseContractMapper, LeaseContractEntity> implements ILeaseContractService {


    @Override
    public void contractAll(ContractVO contractVO, PageParam pageParam) {

    }

    @Override
    public void contractList(ContractVO contractVO, PageParam pageParam) {

    }

    @Override
    public void addContract(ContractVO contractVO) {
        checkForAdd(contractVO);
        LeaseContractEntity contractEntity = new LeaseContractEntity();

    }

    private void checkForAdd(ContractVO contractVO) {

    }

    @Override
    public void deleteContract(Integer id) {

    }
}
