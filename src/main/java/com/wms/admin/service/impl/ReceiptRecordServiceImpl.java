package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.ReceiptRecordEntity;
import com.wms.admin.mapper.ReceiptRecordMapper;
import com.wms.admin.service.IReceiptRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.vo.ReceiptRecordQueryVO;
import com.wms.admin.vo.ReceiptRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 申请记录表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-16 21:10:29
 */
@Service
public class ReceiptRecordServiceImpl extends ServiceImpl<ReceiptRecordMapper, ReceiptRecordEntity> implements IReceiptRecordService {

    @Autowired
    private ReceiptRecordMapper receiptRecordMapper;

    @Override
    public  IPage<ReceiptRecordVO<?>> receiptPage(ReceiptRecordQueryVO queryVO, PageParam pageParam) {
        Page page = new Page(pageParam.getPage(), pageParam.getLimit());
        IPage<ReceiptRecordVO<?>> result = receiptRecordMapper.receiptPage(queryVO, page);
        return result;
    }

    @Override
    public ReceiptRecordVO selectByReceiptNo(String receiptNo) {
        return receiptRecordMapper.selectByReceiptNo(receiptNo);
    }
}
