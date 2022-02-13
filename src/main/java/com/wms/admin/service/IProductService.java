package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.ProductEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.ProdCategoryVO;
import com.wms.admin.vo.ProductQueryVO;
import com.wms.admin.vo.ProductVO;
import com.wms.admin.vo.StoragesRegionVO;

/**
 * <p>
 * 货物表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-13 20:34:22
 */
public interface IProductService extends IService<ProductEntity> {

    IPage<ProductVO> productPages(ProductQueryVO queryVO, PageParam pageParam);

    void addProduct(ProductVO vo);

    void updateProduct(ProductVO categoryVO);

    void deleteProduct(String regionId);
}
