package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.StockChangeRecordEntity;
import com.wms.admin.mapper.StockChangeRecordMapper;
import com.wms.admin.service.IStockService;
import com.wms.admin.vo.StockInventoryQueryVO;
import com.wms.admin.vo.StockInventoryVO;
import com.wms.admin.vo.StockMaintainQueryVO;
import com.wms.admin.vo.StockMaintainVO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品库存变更记录表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-16 21:10:29
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockChangeRecordMapper, StockChangeRecordEntity> implements IStockService {

    @Override
    public IPage<StockMaintainVO> maintainList(StockMaintainQueryVO queryVO, PageParam pageParam) {
        return null;
    }

    @Override
    public void maintainAdd(StockMaintainVO queryVO) {

    }

    @Override
    public void maintainUpdate(StockMaintainVO queryVO) {

    }

    @Override
    public void maintainDelete(String prodNo) {

    }

    @Override
    public IPage<StockInventoryVO> inventoryList(StockInventoryQueryVO queryVO, PageParam pageParam) {
        return null;
    }

    @Override
    public void inventoryAdd(StockInventoryVO queryVO) {

    }

    @Override
    public void inventoryUpdate(StockInventoryVO queryVO) {

    }

    @Override
    public void inventoryDetail(String receiptNo) {

    }

}