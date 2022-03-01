package com.wms.admin.service;

import com.wms.admin.entity.StockMaintainEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.StockMaintainVO;

/**
 * <p>
 * 库存维护记录表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-27 20:57:49
 */
public interface IStockMaintainService extends IService<StockMaintainEntity> {

    void addStockMaintain(StockMaintainVO maintainVO);
    void updateStockMaintain(StockMaintainVO maintainVO);

    void deleteMaintain(Integer id);
}
