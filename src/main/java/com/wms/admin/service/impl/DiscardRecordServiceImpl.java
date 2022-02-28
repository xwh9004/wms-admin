package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.entity.DiscardDetailRecordEntity;
import com.wms.admin.entity.ReceiptRecordEntity;
import com.wms.admin.entity.StorageInDetailRecordEntity;
import com.wms.admin.mapper.DiscardDetailRecordMapper;
import com.wms.admin.service.IDiscardRecordService;
import com.wms.admin.service.IReceiptRecordService;
import com.wms.admin.util.ReceiptUtil;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.DiscardDetailRecordVO;
import com.wms.admin.vo.ReceiptRecordQueryVO;
import com.wms.admin.vo.ReceiptRecordVO;
import com.wms.admin.vo.StorageInDetailRecordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DiscardRecordServiceImpl extends ServiceImpl<DiscardDetailRecordMapper,DiscardDetailRecordEntity> implements IDiscardRecordService {
    @Autowired
    private IReceiptRecordService receiptRecordService;
    @Autowired
    private DiscardDetailRecordMapper discardDetailRecordMapper;


    @Override
    public IPage<ReceiptRecordVO<DiscardDetailRecordVO>> listPage(ReceiptRecordQueryVO queryVO, PageParam pageParam) {
        queryVO.setReceiptType("BF");
        return receiptRecordService.receiptPage(queryVO, pageParam);
    }
    @Override
    public void addDiscard(ReceiptRecordVO<DiscardDetailRecordVO> recordVO) {
        ReceiptRecordEntity receiptRecord = new ReceiptRecordEntity();
        BeanUtils.copyProperties(recordVO, receiptRecord);
        receiptRecord.setId(UUIDUtil.uuid());
        receiptRecord.setReceiptNo(ReceiptUtil.generateNo(recordVO.getReceiptType()));
        receiptRecord.setCreateBy(UserInfoContext.getUsername());
        receiptRecord.setUpdateBy(UserInfoContext.getUsername());
        List<DiscardDetailRecordVO> voList = recordVO.getList();
        if (!voList.isEmpty()) {
            receiptRecord.setProdTypeNums(Integer.valueOf(0));
            receiptRecord.setTotalAmount(Integer.valueOf(0));
            List<DiscardDetailRecordEntity> detailList = new ArrayList<>();
            Set<String> prodIdSet = new HashSet<>();
            BigDecimal totalPrice = BigDecimal.valueOf(0);
            for (DiscardDetailRecordVO item:voList){
                receiptRecord.setTotalAmount(receiptRecord.getTotalAmount()+item.getProdAmount());
                BigDecimal itemTotalPrice = item.getProdUnitPrice().multiply(BigDecimal.valueOf(item.getProdAmount().intValue()));
                totalPrice = totalPrice.add(itemTotalPrice);
                prodIdSet.add(item.getProdId());
                DiscardDetailRecordEntity entity = new DiscardDetailRecordEntity();
                BeanUtils.copyProperties(item, entity,"id");
                entity.setReceiptId(receiptRecord.getId());
                entity.setCreateBy(UserInfoContext.getUsername());
                entity.setUpdateBy(UserInfoContext.getUsername());
                detailList.add(entity);
            }
            totalPrice.multiply(WMSConstants.ONE_HUNDRED);
            receiptRecord.setTotalPrice(totalPrice.intValue());
            receiptRecord.setProdTypeNums(prodIdSet.size());
            saveBatch(detailList);
            receiptRecordService.save(receiptRecord);
        }

    }

    @Override
    public ReceiptRecordVO detail(String receiptNo) {
        ReceiptRecordVO recordVO = receiptRecordService.selectByReceiptNo(receiptNo);
        List<DiscardDetailRecordVO> list = discardDetailRecordMapper.discardDetailListBy(receiptNo);
        list.forEach(item->{
            BigDecimal unitPrice = item.getProdUnitPrice();
            item.setProdUnitPrice(unitPrice.divide(WMSConstants.ONE_HUNDRED));
        });
        recordVO.setList(list);
        return recordVO;
    }
}
