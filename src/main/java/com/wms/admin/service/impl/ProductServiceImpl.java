package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.entity.MeasurementUnitEntity;
import com.wms.admin.entity.ProdCategoryEntity;
import com.wms.admin.entity.ProductEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.MeasurementUnitMapper;
import com.wms.admin.mapper.ProdCategoryMapper;
import com.wms.admin.mapper.ProductMapper;
import com.wms.admin.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.ProductQueryVO;
import com.wms.admin.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;

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

    @Autowired
    private MeasurementUnitMapper unitMapper;
    @Autowired
    private ProdCategoryMapper categoryMapper;

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
        final String unitId = vo.getUnitId();
        final String categoryId = vo.getCategoryId();
        Assert.isTrue(checkUnitIdExist(unitId),"计量单位不存在");
        Assert.isTrue(checkCategoryExist(categoryId),"货物大类不存在");
        Assert.isTrue(!checkProdNoExist(vo.getProdNo()),"货物编号已存在");

    }

    private boolean checkProdNoExist(String prodNo) {
        LambdaQueryWrapper<ProductEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductEntity::getProdNo,prodNo)
                .eq(ProductEntity::getDelFlag,WMSConstants.DEL_FLG_N);
        return productMapper.exists(queryWrapper);
    }
    private boolean checkProdNoExist(String prodNo,String excludeId) {
        LambdaQueryWrapper<ProductEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductEntity::getProdNo,prodNo)
                .ne(ProductEntity::getId,excludeId)
                .eq(ProductEntity::getDelFlag,WMSConstants.DEL_FLG_N);
        return productMapper.exists(queryWrapper);
    }
    private boolean checkCategoryExist(final String categoryId) {
        LambdaQueryWrapper<ProdCategoryEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProdCategoryEntity::getId,categoryId)
                .eq(ProdCategoryEntity::getDelFlag,WMSConstants.DEL_FLG_N);
        return categoryMapper.exists(queryWrapper);
    }

    private boolean checkUnitIdExist(final String unitId) {
        LambdaQueryWrapper<MeasurementUnitEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MeasurementUnitEntity::getId,unitId)
                .eq(MeasurementUnitEntity::getDelFlag,WMSConstants.DEL_FLG_N);
        return unitMapper.exists(queryWrapper);
    }


    @Override
    public void updateProduct(ProductVO productVO) {
        checkForUpdate(productVO);
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productVO, productEntity);
        productEntity.setUpdateBy(UserInfoContext.getUsername());
        updateById(productEntity);
    }

    private void checkForUpdate(ProductVO vo) {
        Assert.isTrue(checkUnitIdExist(vo.getUnitId()),"计量单位不存在");
        Assert.isTrue(checkCategoryExist(vo.getCategoryId()),"货物大类不存在");
        Assert.isTrue(!checkProdNoExist(vo.getProdNo(),vo.getId()),"货物编号已存在");
    }

    @Override
    public void deleteProduct(String id) {
        checkForDelete(id);
        ProductEntity productEntity = baseMapper.selectById(id);
        productEntity.setDelFlag(WMSConstants.DEL_FLG_Y);
        productEntity.setUpdateBy(UserInfoContext.getUsername());
        updateById(productEntity);
    }

    private void checkForDelete(String prodId) {
        //TODO 是否有有效合同
    }
}
