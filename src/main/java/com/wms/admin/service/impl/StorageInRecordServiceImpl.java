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
import com.wms.admin.mapper.StorageInDetailRecordMapper;
import com.wms.admin.service.IReceiptRecordService;
import com.wms.admin.service.IRegionRacksService;
import com.wms.admin.service.IStorageInRecordService;
import com.wms.admin.util.ReceiptUtil;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.ReceiptRecordQueryVO;
import com.wms.admin.vo.ReceiptRecordVO;
import com.wms.admin.vo.RegionRackVO;
import com.wms.admin.vo.StorageInDetailRecordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        BeanUtils.copyProperties(recordVO, recordEntity);
        recordEntity.setId(UUIDUtil.uuid());
        recordEntity.setReceiptNo(ReceiptUtil.generateNo(recordVO.getReceiptType()));
        recordEntity.setCreateBy(UserInfoContext.getUsername());
        recordEntity.setUpdateBy(UserInfoContext.getUsername());
        List<StorageInDetailRecordVO> voList = recordVO.getList();
        if (!voList.isEmpty()) {
            recordEntity.setProdTypeNums(Integer.valueOf(0));
            recordEntity.setTotalAmount(Integer.valueOf(0));
            List<StorageInDetailRecordEntity> detailList = new ArrayList<>();
            Set<String> prodIdSet = new HashSet<>();
            voList.forEach(item -> {
                recordEntity.setTotalAmount(recordEntity.getTotalAmount()+item.getProdAmount());
                prodIdSet.add(item.getProdId());
                StorageInDetailRecordEntity entity = new StorageInDetailRecordEntity();
                BeanUtils.copyProperties(item, entity,"id");
                entity.setReceiptId(recordEntity.getId());
                entity.setCreateBy(UserInfoContext.getUsername());
                entity.setUpdateBy(UserInfoContext.getUsername());
                detailList.add(entity);

            });
            recordEntity.setProdTypeNums(prodIdSet.size());
            saveBatch(detailList);
            receiptRecordService.save(recordEntity);
        }
    }

    private void checkForAdd(ReceiptRecordVO recordVO) {
    }

    @Override
    public ReceiptRecordVO detail(String receiptNo) {

        ReceiptRecordVO recordVO = receiptRecordService.selectByReceiptNo(receiptNo);

        List<StorageInDetailRecordVO> list = storageInDetailRecordMapper.storageInDetailList(receiptNo);
        if (!list.isEmpty()) {
            List<StorageInDetailRecordVO> storageInList = new ArrayList<>();
            list.forEach(item -> {
                StorageInDetailRecordVO vo = new StorageInDetailRecordVO();
                BeanUtils.copyProperties(item, vo);
                storageInList.add(vo);
            });
            recordVO.setList(storageInList);
        }
        return recordVO;
    }

    @Override
    public List<RegionRackVO> regionRacks(String regionId) {
        LambdaQueryWrapper<RegionRacksEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(RegionRacksEntity::getDelFlag,WMSConstants.DEL_FLG_1)
                .eq(RegionRacksEntity::getRegionId,regionId)
        .select(RegionRacksEntity::getId,RegionRacksEntity::getRackNo);
        List<RegionRacksEntity> list = regionRacksService.list(queryWrapper);
        List<RegionRackVO> regionRackVOS = new ArrayList<>();
        list.forEach(item->{
            RegionRackVO vo = new RegionRackVO();
            vo.setId(item.getId());
            vo.setRackNo(item.getRackNo());
            regionRackVOS.add(vo);
        });
        return regionRackVOS;
    }
}
