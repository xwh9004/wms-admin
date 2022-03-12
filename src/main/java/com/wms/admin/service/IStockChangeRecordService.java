package com.wms.admin.service;

import com.wms.admin.entity.StockChangeRecordEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.entity.StorageOutDetailRecordEntity;
import com.wms.admin.vo.ReceiptRecordVO;
import com.wms.admin.vo.StockChangeRecordVO;
import com.wms.admin.vo.StorageOutDetailRecordVO;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * <p>
 * 产品库存变更记录表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-26 14:22:49
 */
public interface IStockChangeRecordService extends IService<StockChangeRecordEntity> {

  void addStocks(List<StockChangeRecordVO> changeRecordVOS);

  void subStocks(List<StockChangeRecordVO> changeRecordVOS);

  <T>List<StockChangeRecordVO> buildStockChangeRecordParams(ReceiptRecordVO<T> recordVO, BiConsumer<StockChangeRecordVO,T> consumer);
}
