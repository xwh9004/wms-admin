package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.dto.ReceiptRecordDto;
import com.wms.admin.entity.ShiftDetailRecordEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.StorageShiftDetailRecordMapper;
import com.wms.admin.service.IReceiptRecordService;
import com.wms.admin.service.IStockChangeRecordService;
import com.wms.admin.service.IStorageShiftRecordService;
import com.wms.admin.vo.ReceiptRecordQueryVO;
import com.wms.admin.vo.ReceiptRecordVO;
import com.wms.admin.vo.StockChangeRecordVO;
import com.wms.admin.vo.StorageShiftDetailRecordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 调拨详情记录表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-18 12:17:42
 */
@Service
public class StorageShiftRecordServiceImpl extends ServiceImpl<StorageShiftDetailRecordMapper, ShiftDetailRecordEntity> implements IStorageShiftRecordService {
    @Autowired
    private StorageShiftDetailRecordMapper storageShiftDetailRecordMapper;

    @Autowired
    private IReceiptRecordService receiptRecordService;
    @Autowired
    private IStockChangeRecordService stockChangeRecordService;

    @Override
    public IPage<ReceiptRecordVO<StorageShiftDetailRecordVO>> listPage(ReceiptRecordQueryVO queryVO, PageParam pageParam) {
        queryVO.setReceiptType("DB");
        Page page = new Page(pageParam.getPage(), pageParam.getLimit());
        return storageShiftDetailRecordMapper.receiptPage(queryVO, page);
    }


    @Transactional
    @Override
    public void addStorageShift(ReceiptRecordVO<StorageShiftDetailRecordVO> recordVO) {
        checkForAdd(recordVO);
        final ReceiptRecordDto<StorageShiftDetailRecordVO> recordDto = new ReceiptRecordDto<>();
        BeanUtils.copyProperties(recordVO, recordDto);
        List<ShiftDetailRecordEntity> detailList = new ArrayList<>();
        receiptRecordService.saveReceiptRecord(recordDto, (item, prodItem) -> {
            ShiftDetailRecordEntity entity = buildDetailEntity(item);
            entity.setReceiptId(recordDto.getId());
            detailList.add(entity);
            prodItem.setProdId(entity.getProdId());
            prodItem.setProdAmount(entity.getProdAmount());
            prodItem.setUnitPrice(entity.getProdUnitPrice());
            entity.setReceiptId(recordDto.getId());
        });
        saveBatch(detailList);
        stockChangeRecordService.addStocks(buildFromRegionStockChangeRecordParams(recordDto));
        stockChangeRecordService.subStocks(buildToRegionStockChangeRecordParams(recordDto));
    }
    private List<StockChangeRecordVO> buildFromRegionStockChangeRecordParams(ReceiptRecordDto<StorageShiftDetailRecordVO> recordDto) {
        return stockChangeRecordService.buildStockChangeRecordParams(recordDto, (vo, detail) -> {
            vo.setProdNo(detail.getProdNo());
            vo.setProdId(detail.getProdId());
            vo.setChangeStock(0-detail.getProdAmount());
            vo.setRegionId(recordDto.getFromId());
            vo.setRegionName(recordDto.getFromName());
        });
    }
    private List<StockChangeRecordVO> buildToRegionStockChangeRecordParams(ReceiptRecordDto<StorageShiftDetailRecordVO> recordDto) {
        return stockChangeRecordService.buildStockChangeRecordParams(recordDto, (vo, detail) -> {
            vo.setProdNo(detail.getProdNo());
            vo.setProdId(detail.getProdId());
            vo.setChangeStock(detail.getProdAmount());
            vo.setRegionId(recordDto.getToId());
            vo.setRegionName(recordDto.getToName());
        });
    }

    private ShiftDetailRecordEntity buildDetailEntity(StorageShiftDetailRecordVO item) {
        ShiftDetailRecordEntity entity = new ShiftDetailRecordEntity();
        BeanUtils.copyProperties(item, entity, "id");
        entity.setProdUnitPrice(item.getUnitPrice());
        entity.setCreateBy(UserInfoContext.getUsername());
        entity.setUpdateBy(UserInfoContext.getUsername());
        return entity;
    }


    private void checkForAdd(ReceiptRecordVO<StorageShiftDetailRecordVO> recordVO) {
        recordVO.setReceiptType("DB");
        List<StorageShiftDetailRecordVO> voList = recordVO.getList();
        if (voList.isEmpty()) {
            throw new BusinessException(ResultCode.PARAM_NOT_NULL, "单据明细");
        }
    }

    @Override
    public ReceiptRecordVO detail(String receiptNo) {
        ReceiptRecordVO recordVO = storageShiftDetailRecordMapper.selectByReceiptNo(receiptNo);
        List<StorageShiftDetailRecordVO> list = storageShiftDetailRecordMapper.storageShiftDetailListBy(receiptNo);
        recordVO.setList(list);
        return recordVO;
    }
}
