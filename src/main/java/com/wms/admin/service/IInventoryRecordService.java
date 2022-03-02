package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.InventoryDetailRecordEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.*;

/**
 * <p>
 * 盘点详情录表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-27 20:57:49
 */
public interface IInventoryRecordService extends IService<InventoryDetailRecordEntity> {

    IPage<ReceiptRecordVO<InventoryDetailRecordVO>> inventoryPages(ReceiptRecordQueryVO queryVO, PageParam pageParam);

    void addInventory(ReceiptRecordVO<InventoryDetailRecordVO> recordVO);

    ReceiptRecordVO<InventoryDetailRecordVO> detail(String receiptNo);
}
