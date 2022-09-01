package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.ProductEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.ProductQueryVO;
import com.wms.admin.vo.ProductVO;

/**
 * <p>
 * 货物表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-14 14:43:16
 */
public interface IProductService extends IService<ProductEntity> {
    IPage<ProductVO> productPages(ProductQueryVO queryVO, PageParam pageParam);

    ProductVO findByProdNo(String prodNo);

    void addProduct(ProductVO vo);

    void updateProduct(ProductVO categoryVO);

    void deleteProduct(String regionId);
}
