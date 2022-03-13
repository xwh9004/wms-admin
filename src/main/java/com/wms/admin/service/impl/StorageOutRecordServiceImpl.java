package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.dto.ReceiptRecordDto;
import com.wms.admin.entity.ReceiptRecordEntity;
import com.wms.admin.entity.StorageInDetailRecordEntity;
import com.wms.admin.entity.StorageOutDetailRecordEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.StorageOutDetailRecordMapper;
import com.wms.admin.service.IReceiptRecordService;
import com.wms.admin.service.IStockChangeRecordService;
import com.wms.admin.service.IStorageOutRecordService;
import com.wms.admin.util.ReceiptUtil;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 出库详情记录表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-18 12:17:42
 */
@Service
public class StorageOutRecordServiceImpl extends ServiceImpl<StorageOutDetailRecordMapper, StorageOutDetailRecordEntity> implements IStorageOutRecordService {
    @Autowired
    private IReceiptRecordService receiptRecordService;

    @Autowired
    private StorageOutDetailRecordMapper storageOutDetailRecordMapper;
    @Autowired
    private IStockChangeRecordService stockChangeRecordService;

    @Override
    public IPage<ReceiptRecordVO<StorageOutDetailRecordVO>> listPage(ReceiptRecordQueryVO queryVO, PageParam pageParam) {
        queryVO.setReceiptType("CK");
        return receiptRecordService.receiptPage(queryVO, pageParam);
    }

    @Transactional
    @Override
    public void addStorageOut(ReceiptRecordVO<StorageOutDetailRecordVO> recordVO) {
        checkForAdd(recordVO);
        ReceiptRecordDto<StorageOutDetailRecordVO> recordDto = new ReceiptRecordDto();
        BeanUtils.copyProperties(recordVO, recordDto);
        ArrayList<StorageOutDetailRecordEntity> detailList = new ArrayList<>();
        receiptRecordService.saveReceiptRecord(recordDto, (item, prodItem) -> {
            StorageOutDetailRecordEntity entity = buildDetailEntity(item);
            entity.setReceiptId(recordDto.getId());
            detailList.add(entity);
            prodItem.setProdId(entity.getProdId());
            prodItem.setProdAmount(entity.getProdAmount());
            prodItem.setUnitPrice(entity.getProdUnitPrice());
        });
        saveBatch(detailList);
        stockChangeRecordService.subStocks(buildStockChangeRecordParams(recordDto));

    }

    private StorageOutDetailRecordEntity buildDetailEntity(StorageOutDetailRecordVO item) {
        StorageOutDetailRecordEntity entity = new StorageOutDetailRecordEntity();
        BeanUtils.copyProperties(item, entity, "id");
        entity.setProdUnitPrice(item.getUnitPrice());
        entity.setCreateBy(UserInfoContext.getUsername());
        entity.setUpdateBy(UserInfoContext.getUsername());
        return entity;
    }

    private List<StockChangeRecordVO> buildStockChangeRecordParams(ReceiptRecordDto<StorageOutDetailRecordVO> recordVO) {

        return stockChangeRecordService.buildStockChangeRecordParams(recordVO, (vo, detail) -> {
            vo.setProdId(detail.getProdId());
            vo.setProdNo(detail.getProdNo());
            vo.setChangeStock(0 - detail.getProdAmount());
        });
    }

    private void checkForAdd(ReceiptRecordVO<StorageOutDetailRecordVO> recordVO) {
        if (recordVO.getList().isEmpty()) {
            throw new BusinessException(ResultCode.PARAM_NOT_NULL, "单据明细");
        }
    }

    @Override
    public ReceiptRecordVO detail(String receiptNo) {
        ReceiptRecordVO recordVO = receiptRecordService.selectByReceiptNo(receiptNo);

        List<StorageOutDetailRecordVO> list = storageOutDetailRecordMapper.storageOutDetailListBy(receiptNo);
        if (!list.isEmpty()) {
            List<StorageOutDetailRecordVO> storageInList = new ArrayList<>();
            list.forEach(item -> {
                StorageOutDetailRecordVO vo = new StorageOutDetailRecordVO();
                BeanUtils.copyProperties(item, vo);
                storageInList.add(vo);
            });
            recordVO.setList(storageInList);
        }
        return recordVO;
    }
}
