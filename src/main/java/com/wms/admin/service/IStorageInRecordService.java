package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.StorageInDetailRecordEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.ReceiptRecordQueryVO;
import com.wms.admin.vo.ReceiptRecordVO;
import com.wms.admin.vo.RegionRackVO;
import com.wms.admin.vo.StorageInDetailRecordVO;

import java.util.List;

/**
 * <p>
 * 产品库存变更记录表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-16 21:10:29
 */
public interface IStorageInRecordService extends IService<StorageInDetailRecordEntity> {

    IPage<ReceiptRecordVO<StorageInDetailRecordVO>> listPage(ReceiptRecordQueryVO queryVO, PageParam pageParam);


    void addStorageIn(ReceiptRecordVO<StorageInDetailRecordVO> recordVO);

    ReceiptRecordVO detail(String receiptNo);

    List<RegionRackVO> regionRacks(String regionId);
}
