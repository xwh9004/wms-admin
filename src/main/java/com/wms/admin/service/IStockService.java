package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.StockChangeRecordEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.*;

/**
 * <p>
 * 产品库存变更记录表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-16 21:10:29
 */
public interface IStockService extends IService<StockChangeRecordEntity> {

    IPage<StockMaintainVO> maintainList(StockMaintainQueryVO queryVO, PageParam pageParam);

    void maintainAdd(StockMaintainVO maintainVO);

    void maintainUpdate(StockMaintainVO maintainVO);

    void maintainDelete(Integer id);
}
