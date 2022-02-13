package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.ProdCategoryEntity;
import com.wms.admin.entity.ProductEntity;
import com.wms.admin.mapper.ProductMapper;
import com.wms.admin.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.vo.ProdCategoryVO;
import com.wms.admin.vo.ProductQueryVO;
import com.wms.admin.vo.ProductVO;
import com.wms.admin.vo.StoragesRegionVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.wms.admin.commom.WMSConstants.DEL_FLG_1;

/**
 * <p>
 * 货物表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-13 20:34:22
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductEntity> implements IProductService {

    @Override
    public IPage<ProductVO> productPages(ProductQueryVO queryVO, PageParam pageParam) {
        IPage<ProductEntity> page = new Page<>(pageParam.getPage(), pageParam.getLimit());
        LambdaQueryWrapper<ProductEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductEntity::getDelFlag, DEL_FLG_1);
        if (StringUtils.isNotBlank(queryVO.getProductName())) {
            queryWrapper.like(ProductEntity::getProductName, queryVO.getProductName());
        }
        if (StringUtils.isNotBlank(queryVO.getProdNo())) {
            queryWrapper.like(ProductEntity::getProdNo, queryVO.getProdNo());
        }
        if (StringUtils.isNotBlank(queryVO.getVendor())) {
            queryWrapper.like(ProductEntity::getVendor, queryVO.getVendor());
        }
        queryWrapper.orderByDesc(ProductEntity::getCreateTime);
        IPage<ProductVO> resultPage = page(page, queryWrapper).convert(entity -> {
            ProductVO vo = new ProductVO();
            BeanUtils.copyProperties(entity, vo);
            return vo;
        });
        return resultPage;
    }

    @Override
    public void addProduct(ProductVO vo) {

    }

    @Override
    public void updateProduct(ProductVO categoryVO) {

    }

    @Override
    public void deleteProduct(String regionId) {

    }
}
