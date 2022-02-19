package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.vo.DiscardDetailRecordVO;
import com.wms.admin.vo.ReceiptRecordQueryVO;
import com.wms.admin.vo.ReceiptRecordVO;

public interface IDiscardRecordService {
    void addDiscard(ReceiptRecordVO<DiscardDetailRecordVO> recordVO);

    IPage<ReceiptRecordVO<DiscardDetailRecordVO>> listPage(ReceiptRecordQueryVO queryVO, PageParam pageParam);

    ReceiptRecordVO detail(String receiptNo);
}
