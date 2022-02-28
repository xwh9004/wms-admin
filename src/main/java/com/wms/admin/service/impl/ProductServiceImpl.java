package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.entity.ProdCategoryEntity;
import com.wms.admin.entity.ProductEntity;
import com.wms.admin.mapper.ProductMapper;
import com.wms.admin.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.ProdCategoryVO;
import com.wms.admin.vo.ProductQueryVO;
import com.wms.admin.vo.ProductVO;
import com.wms.admin.vo.StoragesRegionVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

    @Autowired
    private ProductMapper productMapper;

    private static BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    @Override
    public IPage<ProductVO> productPages(ProductQueryVO queryVO, PageParam pageParam) {
        Page<ProductEntity> page = new Page<>(pageParam.getPage(), pageParam.getLimit());
        IPage<ProductVO> resultPage = productMapper.productPage(queryVO, page);
        return resultPage;
    }

    @Override
    public ProductVO findByProdNo(String prodNo) {
        ProductVO productVO = productMapper.findByProdNo(prodNo);
        return productVO;
    }

    @Override
    public void addProduct(ProductVO vo) {
        checkForAdd(vo);
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(vo, productEntity);
        productEntity.setId(UUIDUtil.uuid());

        productEntity.setCreateBy(UserInfoContext.getUsername());
        productEntity.setUpdateBy(UserInfoContext.getUsername());
        save(productEntity);
    }

    private void checkForAdd(ProductVO vo) {
    }

    @Override
    public void updateProduct(ProductVO productVO) {
        checkForUpdate(productVO);
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productVO, productEntity);
        productEntity.setUpdateBy(UserInfoContext.getUsername());
        updateById(productEntity);
    }

    private void checkForUpdate(ProductVO productVO) {

    }

    @Override
    public void deleteProduct(String id) {
        checkForDelete(id);
        ProductEntity productEntity = baseMapper.selectById(id);
        productEntity.setDelFlag(WMSConstants.DEL_FLG_0);
        productEntity.setUpdateBy(UserInfoContext.getUsername());
        updateById(productEntity);
    }

    private void checkForDelete(String prodId) {

    }
}
