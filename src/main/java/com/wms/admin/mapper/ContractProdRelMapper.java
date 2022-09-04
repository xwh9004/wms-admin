package com.wms.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.ContractProdRelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.admin.vo.ContractProductQueryVO;
import com.wms.admin.vo.ProductVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 合同货物关系表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
public interface ContractProdRelMapper extends BaseMapper<ContractProdRelEntity> {

    IPage<ProductVO> contractProductList(@Param("ps") ContractProductQueryVO queryVO, Page page);
}
