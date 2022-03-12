package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.entity.ReceiptRecordEntity;
import com.wms.admin.entity.RegionRacksEntity;
import com.wms.admin.entity.StorageInDetailRecordEntity;
import com.wms.admin.mapper.StockChangeRecordMapper;
import com.wms.admin.mapper.StockMapper;
import com.wms.admin.mapper.StorageInDetailRecordMapper;
import com.wms.admin.service.IReceiptRecordService;
import com.wms.admin.service.IRegionRacksService;
import com.wms.admin.service.IStockChangeRecordService;
import com.wms.admin.service.IStorageInRecordService;
import com.wms.admin.util.ReceiptUtil;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.zip.DataFormatException;

/**
 * <p>
 * 产品库存变更记录表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-16 21:10:29
 */
@Service
public class StorageInRecordServiceImpl extends ServiceImpl<StorageInDetailRecordMapper, StorageInDetailRecordEntity> implements IStorageInRecordService {
    @Autowired
    private IReceiptRecordService receiptRecordService;
    @Autowired
    private IRegionRacksService regionRacksService;
    @Autowired
    private StorageInDetailRecordMapper storageInDetailRecordMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private IStockChangeRecordService stockChangeRecordService;

    @Override
    public IPage<ReceiptRecordVO<StorageInDetailRecordVO>> listPage(ReceiptRecordQueryVO queryVO, PageParam pageParam) {
        queryVO.setReceiptType("RK");
        return receiptRecordService.receiptPage(queryVO, pageParam);
    }

    @Transactional
    @Override
    public void addStorageIn(ReceiptRecordVO<StorageInDetailRecordVO> recordVO) {
        checkForAdd(recordVO);
        final ReceiptRecordEntity recordEntity = new ReceiptRecordEntity();
        String receiptNo = ReceiptUtil.generateNo(recordVO.getReceiptType());
        recordVO.setReceiptNo(receiptNo);
        BeanUtils.copyProperties(recordVO, recordEntity);
        recordEntity.setId(UUIDUtil.uuid());
        recordEntity.setCreateBy(UserInfoContext.getUsername());
        recordEntity.setUpdateBy(UserInfoContext.getUsername());
        List<StorageInDetailRecordVO> voList = recordVO.getList();
        if (!voList.isEmpty()) {
            recordEntity.setProdTypeNums(Integer.valueOf(0));
            recordEntity.setTotalAmount(Integer.valueOf(0));
            List<StorageInDetailRecordEntity> detailList = new ArrayList<>();
            Money totalPrice = Money.valueOf(BigDecimal.ZERO);
            Set<String> prodIdSet = new HashSet<>();
            for (StorageInDetailRecordVO item : voList) {
                recordEntity.setTotalAmount(recordEntity.getTotalAmount() + item.getProdAmount());
                Money itemTotalPrice = item.getUnitPrice().multiply(item.getProdAmount().intValue());
                totalPrice = totalPrice.add(itemTotalPrice);
                prodIdSet.add(item.getProdId());
                StorageInDetailRecordEntity entity = new StorageInDetailRecordEntity();
                BeanUtils.copyProperties(item, entity, "id");
                entity.setReceiptId(recordEntity.getId());
                entity.setProdUnitPrice(item.getUnitPrice());
                entity.setCreateBy(UserInfoContext.getUsername());
                entity.setUpdateBy(UserInfoContext.getUsername());
                detailList.add(entity);
            }
            recordEntity.setTotalPrice(totalPrice);
            recordEntity.setProdTypeNums(prodIdSet.size());
            saveBatch(detailList);
            receiptRecordService.save(recordEntity);
            stockChangeRecordService.addStocks(buildStockChangeRecordParams(recordVO));

        }
    }

    private List<StockChangeRecordVO> buildStockChangeRecordParams(ReceiptRecordVO<StorageInDetailRecordVO> recordVO) {

        return stockChangeRecordService.buildStockChangeRecordParams(recordVO,(vo,detail)->{
            vo.setProdNo(detail.getProdNo());
            vo.setProdId(detail.getProdId());
            vo.setChangeStock(detail.getProdAmount());
        });
    }

    private void saveStockChangeRecord(List<StockChangeRecordVO> changeRecordVOS) {

    }

    private void saveStock() {

    }


    private void checkForAdd(ReceiptRecordVO recordVO) {
    }

    @Override
    public ReceiptRecordVO detail(String receiptNo) {
        ReceiptRecordVO recordVO = receiptRecordService.selectByReceiptNo(receiptNo);
        List<StorageInDetailRecordVO> list = storageInDetailRecordMapper.storageInDetailListBy(receiptNo);
        recordVO.setList(list);
        return recordVO;
    }

    @Override
    public List<RegionRackVO> regionRacks(String regionId) {
        LambdaQueryWrapper<RegionRacksEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(RegionRacksEntity::getDelFlag, WMSConstants.DEL_FLG_1)
                .eq(RegionRacksEntity::getRegionId, regionId)
                .select(RegionRacksEntity::getId, RegionRacksEntity::getRackNo);
        List<RegionRacksEntity> list = regionRacksService.list(queryWrapper);
        List<RegionRackVO> regionRackVOS = new ArrayList<>();
        list.forEach(item -> {
            RegionRackVO vo = new RegionRackVO();
            vo.setId(item.getId());
            vo.setRackNo(item.getRackNo());
            regionRackVOS.add(vo);
        });
        return regionRackVOS;
    }
}
