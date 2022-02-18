package com.wms.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.entity.ProductEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.admin.vo.ProductQueryVO;
import com.wms.admin.vo.ProductVO;
import com.wms.admin.vo.RegionRackQueryVO;
import com.wms.admin.vo.RegionRackVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 货物表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-02-14 14:43:16
 */
public interface ProductMapper extends BaseMapper<ProductEntity> {
    IPage<ProductVO> productPage(@Param(value = "param") ProductQueryVO queryVO, Page page);

    ProductVO findByProdNo(@Param(value = "prodNo") String prodNo);
}
