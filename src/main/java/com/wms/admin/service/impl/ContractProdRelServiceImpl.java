package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.ContractProdRelEntity;
import com.wms.admin.mapper.ContractProdRelMapper;
import com.wms.admin.service.IContractProdRelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.vo.ContractProductQueryVO;
import com.wms.admin.vo.ProductVO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 合同货物关系表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
@Service
public class ContractProdRelServiceImpl extends ServiceImpl<ContractProdRelMapper, ContractProdRelEntity> implements IContractProdRelService {


    @Override
    public IPage<ProductVO> queryContractProductList(ContractProductQueryVO queryVO, PageParam pageParam) {

        Page page = Page.of(pageParam.getPage(), pageParam.getLimit());

        return baseMapper.contractProductList(queryVO, page);
    }
}
