package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.StorageOutDetailRecordEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.ReceiptRecordQueryVO;
import com.wms.admin.vo.ReceiptRecordVO;
import com.wms.admin.vo.StorageOutDetailRecordVO;

/**
 * <p>
 * 出库详情记录表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-18 12:17:42
 */
public interface IStorageOutRecordService extends IService<StorageOutDetailRecordEntity> {

    IPage<ReceiptRecordVO<StorageOutDetailRecordVO>> listPage(ReceiptRecordQueryVO queryVO, PageParam pageParam);

    void addStorageOut(ReceiptRecordVO<StorageOutDetailRecordVO> recordVO);

    ReceiptRecordVO detail(String receiptNo);
}
