package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.entity.ProductEntity;
import com.wms.admin.entity.StockMaintainEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.StockMaintainMapper;
import com.wms.admin.service.IProductService;
import com.wms.admin.service.IStockMaintainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.vo.StockMaintainQueryVO;
import com.wms.admin.vo.StockMaintainVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 库存维护记录表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-27 20:57:49
 */
@Slf4j
@Service
public class StockMaintainServiceImpl extends ServiceImpl<StockMaintainMapper, StockMaintainEntity> implements IStockMaintainService {

    @Autowired
    private IProductService productService;

    @Autowired
    private StockMaintainMapper stockMaintainMapper;

    @Override
    public void addStockMaintain(StockMaintainVO maintainVO) {
        checkForAdd(maintainVO);
        StockMaintainEntity stockMaintainEntity = new StockMaintainEntity();
        BeanUtils.copyProperties(maintainVO, stockMaintainEntity);
        stockMaintainEntity.setCreateBy(UserInfoContext.getUsername());
        stockMaintainEntity.setUpdateBy(UserInfoContext.getUsername());
        save(stockMaintainEntity);
    }

    private void checkForAdd(StockMaintainVO maintainVO) {
        checkProdExistMaintain(maintainVO.getProdId());
        checkProdId(maintainVO.getProdId());
        checkBound(maintainVO);
    }

    private void checkProdExistMaintain(String prodId) {
        checkProdId(prodId);
        LambdaQueryWrapper<StockMaintainEntity> queryCond = new LambdaQueryWrapper<>();
        queryCond
                .eq(StockMaintainEntity::getDelFlag, WMSConstants.DEL_FLG_N)
                .eq(StockMaintainEntity::getProdId, prodId);
        StockMaintainEntity maintainEntity = stockMaintainMapper.selectOne(queryCond);
        if (maintainEntity != null) {
            throw new BusinessException(ResultCode.RESOURCE_EXISTS, "货物库存维护记录");
        }
    }

    private void checkBound(StockMaintainVO maintainVO) {
        Integer downBound = maintainVO.getDownBound();
        Integer upBound = maintainVO.getUpBound();
        if (downBound.compareTo(Integer.valueOf(0)) < 0) {
            throw new BusinessException(ResultCode.PARAM_ERROR, "下限值不能小于0");
        }
        if (upBound.compareTo(Integer.valueOf(0)) < 0) {
            throw new BusinessException(ResultCode.PARAM_ERROR, "上限值不能小于0");
        }
        if (downBound.compareTo(upBound) >= 0) {
            throw new BusinessException(ResultCode.PARAM_ERROR, "下限值不能比上限值大");
        }
    }

    private void checkExist(Integer id) {
        final StockMaintainEntity maintainEntity = getById(id);
        if (maintainEntity == null || WMSConstants.DEL_FLG_Y.equals(maintainEntity.getDelFlag())) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "维护记录" + id);
        }
    }

    private void checkProdId(String prodId) {
        ProductEntity prodEntity = productService.getById(prodId);
        if (prodEntity == null) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "货物");
        }
    }

    @Override
    public void updateStockMaintain(StockMaintainVO maintainVO) {
        checkForUpdate(maintainVO);
        StockMaintainEntity stockMaintainEntity = new StockMaintainEntity();
        stockMaintainEntity.setUpBound(maintainVO.getUpBound());
        stockMaintainEntity.setDownBound(maintainVO.getDownBound());
        stockMaintainEntity.setUpdateBy(UserInfoContext.getUsername());
        updateById(stockMaintainEntity);
    }

    private void checkForUpdate(StockMaintainVO maintainVO) {
        checkExist(maintainVO.getId());
        checkBound(maintainVO);
    }


    @Override
    public void deleteMaintain(Integer id) {
        checkExist(id);
        LambdaUpdateWrapper<StockMaintainEntity> updateQuery = new LambdaUpdateWrapper<>();
        updateQuery.eq(StockMaintainEntity::getId,id)
        .set(StockMaintainEntity::getDelFlag,WMSConstants.DEL_FLG_Y)
        .set(StockMaintainEntity::getUpdateBy,UserInfoContext.getUsername());
        update(updateQuery);
    }

    @Override
    public IPage<StockMaintainVO> maintainPages(StockMaintainQueryVO queryVO, PageParam pageParam) {
        Page page = new Page(pageParam.getPage(), pageParam.getLimit());
        IPage<StockMaintainVO> result =stockMaintainMapper.maintainPages(queryVO,page);
        return result;
    }
}
