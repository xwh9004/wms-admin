package com.wms.admin.service.impl;

import com.wms.admin.entity.StockChangeRecordEntity;
import com.wms.admin.mapper.StockChangeRecordMapper;
import com.wms.admin.service.IStockChangeRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
