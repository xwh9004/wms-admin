package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.interfaces.Func;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.dto.ProdItemDto;
import com.wms.admin.dto.ReceiptRecordDto;
import com.wms.admin.entity.ReceiptRecordEntity;
import com.wms.admin.mapper.ReceiptRecordMapper;
import com.wms.admin.service.IReceiptRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.util.ReceiptUtil;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.Money;
import com.wms.admin.vo.ReceiptRecordQueryVO;
import com.wms.admin.vo.ReceiptRecordVO;
import com.wms.admin.vo.StorageInDetailRecordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

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
    public IPage<ReceiptRecordVO<?>> receiptPage(ReceiptRecordQueryVO queryVO, PageParam pageParam) {
        Page page = new Page(pageParam.getPage(), pageParam.getLimit());
        IPage<ReceiptRecordVO<?>> result = receiptRecordMapper.receiptPage(queryVO, page);
        return result;
    }

    @Transactional
    @Override
    public <T> ReceiptRecordDto saveReceiptRecord(ReceiptRecordDto<T> receiptRecordDto, BiConsumer<T, ProdItemDto> consumer) {
        final ReceiptRecordEntity recordEntity = new ReceiptRecordEntity();
        receiptRecordDto.setId(UUIDUtil.uuid());
        receiptRecordDto.setReceiptNo(ReceiptUtil.generateNo(receiptRecordDto.getReceiptType()));
        BeanUtils.copyProperties(receiptRecordDto, recordEntity);
        recordEntity.setCreateBy(UserInfoContext.getUsername());
        recordEntity.setUpdateBy(UserInfoContext.getUsername());
        Integer totalAmount = Integer.valueOf(0);
        Money totalPrice = Money.valueOf(BigDecimal.ZERO);
        List<T> voList = receiptRecordDto.getList();
        Set<String> prodIdSet = new HashSet<>();
        for (T item : voList) {
            ProdItemDto prodItem = new ProdItemDto();
            consumer.accept(item, prodItem);
            Money itemTotalPrice = prodItem.getUnitPrice().multiply(prodItem.getProdAmount().intValue());
            totalAmount = totalAmount.intValue() + prodItem.getProdAmount();
            totalPrice = totalPrice.add(itemTotalPrice);
            prodIdSet.add(prodItem.getProdId());
        }
        recordEntity.setTotalAmount(totalAmount);
        recordEntity.setTotalPrice(totalPrice);
        recordEntity.setProdTypeNums(prodIdSet.size());
        save(recordEntity);
        return receiptRecordDto;
    }

    @Override
    public ReceiptRecordVO selectByReceiptNo(String receiptNo) {
        return receiptRecordMapper.selectByReceiptNo(receiptNo);
    }
}
