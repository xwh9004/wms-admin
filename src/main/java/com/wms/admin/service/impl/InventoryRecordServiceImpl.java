package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.InventoryDetailRecordEntity;
import com.wms.admin.entity.ReceiptRecordEntity;
import com.wms.admin.mapper.InventoryDetailRecordMapper;
import com.wms.admin.service.IInventoryRecordService;
import com.wms.admin.service.IReceiptRecordService;
import com.wms.admin.util.ReceiptUtil;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        final ReceiptRecordEntity recordEntity = new ReceiptRecordEntity();
        BeanUtils.copyProperties(recordVO, recordEntity);
        recordEntity.setId(UUIDUtil.uuid());
        recordEntity.setReceiptNo(ReceiptUtil.generateNo(recordVO.getReceiptType()));
        recordEntity.setCreateBy(UserInfoContext.getUsername());
        recordEntity.setUpdateBy(UserInfoContext.getUsername());
        List<InventoryDetailRecordVO> voList = recordVO.getList();
        if (!voList.isEmpty()) {
            recordEntity.setProdTypeNums(Integer.valueOf(0));
            recordEntity.setTotalAmount(Integer.valueOf(0));
            Money totalPrice = Money.valueOf(BigDecimal.ZERO);
            List<InventoryDetailRecordEntity> detailList = new ArrayList<>();
            Set<String> prodIdSet = new HashSet<>();
            for (InventoryDetailRecordVO item : voList) {
                recordEntity.setTotalAmount(recordEntity.getTotalAmount() + item.getProdAmount());
                Money itemTotalPrice = item.getProdUnitPrice().multiply(BigDecimal.valueOf(item.getProdAmount()));
                totalPrice = totalPrice.add(itemTotalPrice);
                prodIdSet.add(item.getProdId());
                InventoryDetailRecordEntity entity = new InventoryDetailRecordEntity();
                BeanUtils.copyProperties(item, entity, "id");
                entity.setReceiptId(recordEntity.getId());
                entity.setCreateBy(UserInfoContext.getUsername());
                entity.setUpdateBy(UserInfoContext.getUsername());
                detailList.add(entity);

            }
            ;
            recordEntity.setTotalPrice(totalPrice);
            recordEntity.setProdTypeNums(prodIdSet.size());
            saveBatch(detailList);
            receiptRecordService.save(recordEntity);
        }
    }

    private void checkForAdd(ReceiptRecordVO<InventoryDetailRecordVO> recordVO) {


    }

    @Override
    public ReceiptRecordVO<InventoryDetailRecordVO> detail(String receiptNo) {
        ReceiptRecordVO recordVO = receiptRecordService.selectByReceiptNo(receiptNo);
        List<InventoryDetailRecordVO> list = inventoryDetailRecordMapper.inventoryDetailListBy(receiptNo);
        recordVO.setList(list);
        return recordVO;
    }
}
