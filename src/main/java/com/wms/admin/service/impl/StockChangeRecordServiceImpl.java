package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.dto.ReceiptRecordDto;
import com.wms.admin.entity.StockChangeRecordEntity;
import com.wms.admin.entity.StockEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.StockChangeRecordMapper;
import com.wms.admin.service.IStockChangeRecordService;
import com.wms.admin.service.IStockService;
import com.wms.admin.vo.ReceiptRecordVO;
import com.wms.admin.vo.StockChangeRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/**
 * <p>
 * 产品库存变更记录表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-26 14:22:49
 */
@Service
public class StockChangeRecordServiceImpl extends ServiceImpl<StockChangeRecordMapper, StockChangeRecordEntity> implements IStockChangeRecordService {
    @Autowired
    private StockChangeRecordMapper stockChangeRecordMapper;

    @Autowired
    private IStockService stockService;

    @Transactional
    @Override
    public void addStocks(List<StockChangeRecordVO> changeRecordVOS) {
        if (CollectionUtils.isEmpty(changeRecordVOS)) {
            return;
        }
        List<StockEntity> stockEntities = getEntities(changeRecordVOS);
        stockChangeRecordMapper.saveChangeRecords(changeRecordVOS);
        stockService.saveOrUpdateBatch(stockEntities);
    }

    private Optional<StockEntity> getStock(String prodId, String regionId) {
        QueryWrapper<StockEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StockEntity::getDelFlag, WMSConstants.DEL_FLG_1)
                .eq(StockEntity::getProdId, prodId).eq(StockEntity::getRegionId, regionId);
        StockEntity stock = stockService.getOne(queryWrapper);
        return Optional.ofNullable(stock);
    }

    @Override
    public void subStocks(List<StockChangeRecordVO> changeRecordVOS) {
        if (CollectionUtils.isEmpty(changeRecordVOS)) {
            return;
        }
        List<StockEntity> stockEntities = getEntities(changeRecordVOS, stock -> stock.getCurrentStock() > 0);
        stockChangeRecordMapper.saveChangeRecords(changeRecordVOS);
        stockService.saveOrUpdateBatch(stockEntities);
    }

    private List<StockEntity> getEntities(List<StockChangeRecordVO> changeRecordVOS) {
        return getEntities(changeRecordVOS, (item) -> true);
    }

    private List<StockEntity> getEntities(List<StockChangeRecordVO> changeRecordVOS, Predicate<StockEntity> remainStockTest) {
        List<StockEntity> stockEntities = new ArrayList<>();
        for (StockChangeRecordVO item : changeRecordVOS) {
            StockEntity stock = getStock(item.getProdId(), item.getRegionId())
                    .orElse(new StockEntity(item.getProdId(), item.getRegionId(), Integer.valueOf(0),UserInfoContext.getUsername()));
            stockEntities.add(stock);
            //assign current_stock to change record item
            item.setCurrentStock(stock.getCurrentStock());
            //addition change stock to current record
            int remainStock = stock.getCurrentStock() + item.getChangeStock();
            stock.setCurrentStock(remainStock);
            stock.setUpdateBy(UserInfoContext.getUsername());
            if (remainStockTest != null && !remainStockTest.test(stock)) {
                throw new BusinessException(ResultCode.STOCK_LACKED, String.format("库区%s产品%s", stock.getRegionId(), stock.getProdId()));
            }
        }
        return stockEntities;
    }

    @Override
    public <T> List<StockChangeRecordVO> buildStockChangeRecordParams(ReceiptRecordDto<T> recordVO, BiConsumer<StockChangeRecordVO, T> consumer) {

        LocalDateTime now = LocalDateTime.now();
        List<T> items = recordVO.getList();
        List<StockChangeRecordVO> changeRecordVOList = new ArrayList<>();
        items.forEach(t -> {
            StockChangeRecordVO changeRecordVO = new StockChangeRecordVO();
            consumer.accept(changeRecordVO, t);
            changeRecordVO.setReceiptNo(recordVO.getReceiptNo());
            changeRecordVO.setRegionId(recordVO.getRegionId());
            changeRecordVO.setRegionName(recordVO.getRegionName());
            changeRecordVO.setCreateBy(UserInfoContext.getUsername());
            changeRecordVO.setUpdateBy(UserInfoContext.getUsername());
            changeRecordVO.setCreateTime(now);
            changeRecordVO.setUpdateTime(now);
            changeRecordVO.setDelFlag(WMSConstants.DEL_FLG_1);
            changeRecordVOList.add(changeRecordVO);
        });
        return changeRecordVOList;
    }
}
