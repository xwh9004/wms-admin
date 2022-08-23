package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.dto.ReceiptRecordDto;
import com.wms.admin.entity.DiscardDetailRecordEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.DiscardDetailRecordMapper;
import com.wms.admin.service.IDiscardRecordService;
import com.wms.admin.service.IReceiptRecordService;
import com.wms.admin.service.IStockChangeRecordService;
import com.wms.admin.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscardRecordServiceImpl extends ServiceImpl<DiscardDetailRecordMapper, DiscardDetailRecordEntity> implements IDiscardRecordService {
    @Autowired
    private IReceiptRecordService receiptRecordService;
    @Autowired
    private DiscardDetailRecordMapper discardDetailRecordMapper;
    @Autowired
    private IStockChangeRecordService stockChangeRecordService;

    @Override
    public IPage<ReceiptRecordVO<DiscardDetailRecordVO>> listPage(ReceiptRecordQueryVO queryVO, PageParam pageParam) {
        queryVO.setReceiptType("BF");
        return receiptRecordService.receiptPage(queryVO, pageParam);
    }

    @Override
    public void addDiscard(ReceiptRecordVO<DiscardDetailRecordVO> recordVO) {
        checkForAdd(recordVO);
        final ReceiptRecordDto<DiscardDetailRecordVO> recordDto = new ReceiptRecordDto<>();
        BeanUtils.copyProperties(recordVO, recordDto);

        List<DiscardDetailRecordEntity> detailList = new ArrayList<>();
        receiptRecordService.saveReceiptRecord(recordDto, (item, prodItem) -> {
            DiscardDetailRecordEntity entity = buildDetailEntity(item);
            detailList.add(entity);
            prodItem.setProdId(entity.getProdId());
            prodItem.setProdAmount(entity.getProdAmount());
            prodItem.setUnitPrice(entity.getProdUnitPrice());
            entity.setReceiptId(recordDto.getId());
        });
        saveBatch(detailList);
        stockChangeRecordService.subStocks(buildStockChangeRecordParams(recordDto));

    }

    private List<StockChangeRecordVO> buildStockChangeRecordParams(ReceiptRecordDto<DiscardDetailRecordVO> recordDto) {
        return stockChangeRecordService.buildStockChangeRecordParams(recordDto, (vo, detail) -> {
            vo.setProdNo(detail.getProdNo());
            vo.setProdId(detail.getProdId());
            vo.setChangeStock(0-detail.getProdAmount());
        });
    }

    private DiscardDetailRecordEntity buildDetailEntity(DiscardDetailRecordVO item) {
        DiscardDetailRecordEntity entity = new DiscardDetailRecordEntity();
        BeanUtils.copyProperties(item, entity, "id");
        entity.setProdUnitPrice(item.getUnitPrice());
        entity.setCreateBy(UserInfoContext.getUsername());
        entity.setUpdateBy(UserInfoContext.getUsername());
        return entity;
    }

    private void checkForAdd(ReceiptRecordVO<DiscardDetailRecordVO> recordVO) {
        List<DiscardDetailRecordVO> voList = recordVO.getList();
        if (voList.isEmpty()) {
            throw new BusinessException(ResultCode.PARAM_NOT_NULL, "单据明细");
        }
    }

    @Override
    public ReceiptRecordVO detail(String receiptNo) {
        ReceiptRecordVO recordVO = receiptRecordService.selectByReceiptNo(receiptNo);
        List<DiscardDetailRecordVO> list = discardDetailRecordMapper.discardDetailListBy(receiptNo);
        recordVO.setList(list);
        return recordVO;
    }
}
