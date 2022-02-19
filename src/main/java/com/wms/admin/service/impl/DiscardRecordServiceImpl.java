package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.service.IDiscardRecordService;
import com.wms.admin.vo.DiscardDetailRecordVO;
import com.wms.admin.vo.ReceiptRecordQueryVO;
import com.wms.admin.vo.ReceiptRecordVO;
import org.springframework.stereotype.Service;

@Service
public class DiscardRecordServiceImpl implements IDiscardRecordService {
    @Override
    public void addDiscard(ReceiptRecordVO<DiscardDetailRecordVO> recordVO) {

    }

    @Override
    public IPage<ReceiptRecordVO<DiscardDetailRecordVO>> listPage(ReceiptRecordQueryVO queryVO, PageParam pageParam) {
        return null;
    }

    @Override
    public ReceiptRecordVO detail(String receiptNo) {
        return null;
    }
}
