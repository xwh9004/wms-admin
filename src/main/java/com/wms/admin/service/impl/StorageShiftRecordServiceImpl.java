package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.ReceiptRecordEntity;
import com.wms.admin.entity.ShiftDetailRecordEntity;
import com.wms.admin.mapper.StorageShiftDetailRecordMapper;
import com.wms.admin.service.IReceiptRecordService;
import com.wms.admin.service.IStorageShiftRecordService;
import com.wms.admin.util.ReceiptUtil;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        final ReceiptRecordEntity recordEntity = new ReceiptRecordEntity();
        BeanUtils.copyProperties(recordVO, recordEntity);
        recordEntity.setId(UUIDUtil.uuid());
        recordEntity.setReceiptNo(ReceiptUtil.generateNo(recordVO.getReceiptType()));
        recordEntity.setCreateBy(UserInfoContext.getUsername());
        recordEntity.setUpdateBy(UserInfoContext.getUsername());
        List<StorageShiftDetailRecordVO> voList = recordVO.getList();
        if (!voList.isEmpty()) {
            recordEntity.setProdTypeNums(Integer.valueOf(0));
            recordEntity.setTotalAmount(Integer.valueOf(0));
            List<ShiftDetailRecordEntity> detailList = new ArrayList<>();
            Set<String> prodIdSet = new HashSet<>();
            Money totalPrice = Money.valueOf(BigDecimal.ZERO);
            for (StorageShiftDetailRecordVO item : voList) {
                recordEntity.setTotalAmount(recordEntity.getTotalAmount() + item.getProdAmount());
                Money itemTotalPrice = item.getUnitPrice().multiply(item.getProdAmount().intValue());
                totalPrice = totalPrice.add(itemTotalPrice);
                prodIdSet.add(item.getProdId());
                ShiftDetailRecordEntity entity = new ShiftDetailRecordEntity();
                BeanUtils.copyProperties(item, entity, "id");
                entity.setProdUnitPrice(item.getUnitPrice());
                entity.setReceiptId(recordEntity.getId());
                entity.setCreateBy(UserInfoContext.getUsername());
                entity.setUpdateBy(UserInfoContext.getUsername());
                detailList.add(entity);
            }
            recordEntity.setTotalPrice(totalPrice);
            recordEntity.setProdTypeNums(prodIdSet.size());
            saveBatch(detailList);
            receiptRecordService.save(recordEntity);
        }
    }

    private void checkForAdd(ReceiptRecordVO<StorageShiftDetailRecordVO> recordVO) {
        recordVO.setReceiptType("DB");
    }

    @Override
    public ReceiptRecordVO detail(String receiptNo) {
        ReceiptRecordVO recordVO = storageShiftDetailRecordMapper.selectByReceiptNo(receiptNo);
        List<StorageShiftDetailRecordVO> list = storageShiftDetailRecordMapper.storageShiftDetailListBy(receiptNo);
        recordVO.setList(list);
        return recordVO;
    }
}
