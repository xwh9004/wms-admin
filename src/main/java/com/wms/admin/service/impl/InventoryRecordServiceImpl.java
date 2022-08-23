package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.dto.ReceiptRecordDto;
import com.wms.admin.entity.InventoryDetailRecordEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.InventoryDetailRecordMapper;
import com.wms.admin.service.IInventoryRecordService;
import com.wms.admin.service.IReceiptRecordService;
import com.wms.admin.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 盘点详情录表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-27 20:57:49
 */
@Service
public class InventoryRecordServiceImpl extends ServiceImpl<InventoryDetailRecordMapper, InventoryDetailRecordEntity> implements IInventoryRecordService {
    @Autowired
    private IReceiptRecordService receiptRecordService;

    @Autowired
    private InventoryDetailRecordMapper inventoryDetailRecordMapper;

    @Override
    public IPage<ReceiptRecordVO<InventoryDetailRecordVO>> inventoryPages(ReceiptRecordQueryVO queryVO, PageParam pageParam) {
        queryVO.setReceiptType("PD");
        return receiptRecordService.receiptPage(queryVO, pageParam);
    }

    @Override
    public void addInventory(ReceiptRecordVO<InventoryDetailRecordVO> recordVO) {
        checkForAdd(recordVO);
        final ReceiptRecordDto<InventoryDetailRecordVO> recordDto = new ReceiptRecordDto<>();
        BeanUtils.copyProperties(recordVO, recordDto);
        List<InventoryDetailRecordEntity> detailList = new ArrayList<>();
        receiptRecordService.saveReceiptRecord(recordDto, (item, prodItem) -> {
            InventoryDetailRecordEntity entity = buildDetailEntity(item);
            detailList.add(entity);
            prodItem.setProdId(entity.getProdId());
            prodItem.setProdAmount(entity.getProdAmount());
            prodItem.setUnitPrice(entity.getProdUnitPrice());
            entity.setReceiptId(recordDto.getId());
        });

        saveBatch(detailList);
    }

    private InventoryDetailRecordEntity buildDetailEntity(InventoryDetailRecordVO item) {
        InventoryDetailRecordEntity entity = new InventoryDetailRecordEntity();
        BeanUtils.copyProperties(item, entity, "id");
        entity.setProdUnitPrice(item.getUnitPrice());
        entity.setCreateBy(UserInfoContext.getUsername());
        entity.setUpdateBy(UserInfoContext.getUsername());
        return entity;
    }


    private void checkForAdd(ReceiptRecordVO<InventoryDetailRecordVO> recordVO) {
        List<InventoryDetailRecordVO> voList = recordVO.getList();
        if (voList.isEmpty()) {
            throw new BusinessException(ResultCode.PARAM_NOT_NULL, "单据明细");
        }

    }

    @Override
    public ReceiptRecordVO<InventoryDetailRecordVO> detail(String receiptNo) {
        ReceiptRecordVO recordVO = receiptRecordService.selectByReceiptNo(receiptNo);
        List<InventoryDetailRecordVO> list = inventoryDetailRecordMapper.inventoryDetailListBy(receiptNo);
        recordVO.setList(list);
        return recordVO;
    }
}
