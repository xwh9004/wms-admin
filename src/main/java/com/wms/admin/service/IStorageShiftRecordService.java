package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.ShiftDetailRecordEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.ReceiptRecordQueryVO;
import com.wms.admin.vo.ReceiptRecordVO;
import com.wms.admin.vo.StorageInDetailRecordVO;
import com.wms.admin.vo.StorageShiftDetailRecordVO;

/**
 * <p>
 * 调拨详情记录表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-18 12:17:42
 */
public interface IStorageShiftRecordService extends IService<ShiftDetailRecordEntity> {

    IPage<ReceiptRecordVO<StorageShiftDetailRecordVO>> listPage(ReceiptRecordQueryVO queryVO, PageParam pageParam);

    ReceiptRecordVO detail(String receiptNo);

    void addStorageShift(ReceiptRecordVO<StorageShiftDetailRecordVO> recordVO);
}
