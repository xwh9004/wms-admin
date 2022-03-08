package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.ReceiptRecordEntity;
import com.wms.admin.entity.StorageInDetailRecordEntity;
import com.wms.admin.entity.StorageOutDetailRecordEntity;
import com.wms.admin.mapper.StorageOutDetailRecordMapper;
import com.wms.admin.service.IReceiptRecordService;
import com.wms.admin.service.IStorageOutRecordService;
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

    @Override
    public IPage<ReceiptRecordVO<StorageOutDetailRecordVO>> listPage(ReceiptRecordQueryVO queryVO, PageParam pageParam) {
        queryVO.setReceiptType("CK");
        return receiptRecordService.receiptPage(queryVO, pageParam);
    }

    @Override
    public void addStorageOut(ReceiptRecordVO<StorageOutDetailRecordVO> recordVO) {
        checkForAdd(recordVO);
        final ReceiptRecordEntity recordEntity = new ReceiptRecordEntity();
        BeanUtils.copyProperties(recordVO, recordEntity);
        recordEntity.setId(UUIDUtil.uuid());
        recordEntity.setReceiptNo(ReceiptUtil.generateNo(recordVO.getReceiptType()));
        recordEntity.setCreateBy(UserInfoContext.getUsername());
        recordEntity.setUpdateBy(UserInfoContext.getUsername());
        List<StorageOutDetailRecordVO> voList = recordVO.getList();
        if (!voList.isEmpty()) {
            recordEntity.setProdTypeNums(Integer.valueOf(0));
            recordEntity.setTotalAmount(Integer.valueOf(0));
            List<StorageOutDetailRecordEntity> detailList = new ArrayList<>();
            Set<String> prodIdSet = new HashSet<>();
            Money totalPrice = Money.valueOf(BigDecimal.ZERO);
            for (StorageOutDetailRecordVO item : voList) {
                recordEntity.setTotalAmount(recordEntity.getTotalAmount() + item.getProdAmount());
                Money itemTotalPrice = item.getUnitPrice().multiply(item.getProdAmount().intValue());
                totalPrice = totalPrice.add(itemTotalPrice);
                prodIdSet.add(item.getProdId());
                StorageOutDetailRecordEntity entity = new StorageOutDetailRecordEntity();
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

    private void checkForAdd(ReceiptRecordVO<StorageOutDetailRecordVO> recordVO) {
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
