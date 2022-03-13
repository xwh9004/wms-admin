package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.dto.ProdItemDto;
import com.wms.admin.dto.ReceiptRecordDto;
import com.wms.admin.entity.ReceiptRecordEntity;
import com.wms.admin.entity.RegionRacksEntity;
import com.wms.admin.entity.StorageInDetailRecordEntity;
import com.wms.admin.exception.BusinessException;
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

        final ReceiptRecordDto<StorageInDetailRecordVO> recordDto = new ReceiptRecordDto<>();
        BeanUtils.copyProperties(recordVO, recordDto);
        List<StorageInDetailRecordEntity> detailList = new ArrayList<>();
        receiptRecordService.saveReceiptRecord(recordDto, (item, prodItem) -> {
            StorageInDetailRecordEntity entity = buildDetailEntity(item);
            detailList.add(entity);
            prodItem.setProdId(entity.getProdId());
            prodItem.setProdAmount(entity.getProdAmount());
            prodItem.setUnitPrice(entity.getProdUnitPrice());
            entity.setReceiptId(recordDto.getId());
        });
        saveBatch(detailList);
        stockChangeRecordService.addStocks(buildStockChangeRecordParams(recordDto));
    }

    private StorageInDetailRecordEntity buildDetailEntity(StorageInDetailRecordVO item) {
        StorageInDetailRecordEntity entity = new StorageInDetailRecordEntity();
        BeanUtils.copyProperties(item, entity, "id");
        entity.setProdUnitPrice(item.getUnitPrice());
        entity.setCreateBy(UserInfoContext.getUsername());
        entity.setUpdateBy(UserInfoContext.getUsername());
        return entity;
    }

    private List<StockChangeRecordVO> buildStockChangeRecordParams(ReceiptRecordDto<StorageInDetailRecordVO> recordVO) {
        return stockChangeRecordService.buildStockChangeRecordParams(recordVO, (vo, detail) -> {
            vo.setProdNo(detail.getProdNo());
            vo.setProdId(detail.getProdId());
            vo.setChangeStock(detail.getProdAmount());
        });
    }


    private void checkForAdd(ReceiptRecordVO recordVO) {
        List<StorageInDetailRecordVO> voList = recordVO.getList();
        if (voList.isEmpty()) {
            throw new BusinessException(ResultCode.PARAM_NOT_NULL, "单据明细");
        }
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
