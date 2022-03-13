package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.dto.ProdItemDto;
import com.wms.admin.dto.ReceiptRecordDto;
import com.wms.admin.entity.ReceiptRecordEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.Money;
import com.wms.admin.vo.ReceiptRecordQueryVO;
import com.wms.admin.vo.ReceiptRecordVO;
import com.wms.admin.vo.StorageInDetailRecordVO;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * <p>
 * 申请记录表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-16 21:10:29
 */
public interface IReceiptRecordService extends IService<ReceiptRecordEntity> {

    <T> ReceiptRecordDto saveReceiptRecord(ReceiptRecordDto<T> receiptRecordDto,BiConsumer<T, ProdItemDto> consumer);

    <T> IPage<ReceiptRecordVO<T>> receiptPage(ReceiptRecordQueryVO queryVO, PageParam pageParam);

    ReceiptRecordVO selectByReceiptNo(String receiptNo);
}
