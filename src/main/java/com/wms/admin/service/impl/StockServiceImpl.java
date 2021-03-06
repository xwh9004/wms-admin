package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.StockChangeRecordEntity;
import com.wms.admin.entity.StockEntity;
import com.wms.admin.mapper.StockChangeRecordMapper;
import com.wms.admin.mapper.StockMapper;
import com.wms.admin.service.IStockMaintainService;
import com.wms.admin.service.IStockService;
import com.wms.admin.vo.StockMaintainQueryVO;
import com.wms.admin.vo.StockMaintainVO;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StockServiceImpl extends ServiceImpl<StockMapper, StockEntity> implements IStockService {
    
    @Autowired
    private IStockMaintainService stockMaintainService;
    
    @Override
    public IPage<StockMaintainVO> maintainList(StockMaintainQueryVO queryVO, PageParam pageParam) {
        return stockMaintainService.maintainPages(queryVO,pageParam);
    }

    @Override
    public void maintainAdd(StockMaintainVO maintainVO) {
        stockMaintainService.addStockMaintain(maintainVO);
    }

   

    @Override
    public void maintainUpdate(StockMaintainVO maintainVO) {
        stockMaintainService.updateStockMaintain(maintainVO);
    }

    @Override
    public void maintainDelete(Integer id) {
        stockMaintainService.deleteMaintain(id);
    }


}
