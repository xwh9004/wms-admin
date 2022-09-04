package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.ContractProdRelEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.ContractProductQueryVO;
import com.wms.admin.vo.ProductVO;

/**
 * <p>
 * 合同货物关系表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
public interface IContractProdRelService extends IService<ContractProdRelEntity> {

    IPage<ProductVO> queryContractProductList(ContractProductQueryVO queryVO, PageParam pageParam);

}
