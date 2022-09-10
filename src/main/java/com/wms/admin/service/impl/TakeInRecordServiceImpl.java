package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.entity.LeaseContractEntity;
import com.wms.admin.entity.TakeInDetailEntity;
import com.wms.admin.entity.TakeInRecordEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.LeaseContractMapper;
import com.wms.admin.mapper.TakeInRecordMapper;
import com.wms.admin.service.ITakeInDetailService;
import com.wms.admin.service.ITakeInRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.util.SequenceUtil;
import com.wms.admin.util.WmsUtils;
import com.wms.admin.vo.Money;
import com.wms.admin.vo.TakeInProdVO;
import com.wms.admin.vo.TakeInQueryVO;
import com.wms.admin.vo.TakeInVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 收货记录表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
@Service
public class TakeInRecordServiceImpl extends ServiceImpl<TakeInRecordMapper, TakeInRecordEntity> implements ITakeInRecordService {

    @Autowired
    private ITakeInDetailService takeInDetailService;

    @Autowired
    private LeaseContractMapper leaseContractMapper;

    @Override
    public IPage<TakeInVO> takeInList(TakeInQueryVO queryVO, PageParam pageParam) {
        WmsUtils.checkDateRange(queryVO.getTakeInStartTime(), queryVO.getTakeInEndTime());

        Page page = Page.of(pageParam.getPage(), pageParam.getLimit());

        LambdaQueryWrapper<TakeInRecordEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(queryVO.getTakeInNo())) {
            queryWrapper.like(TakeInRecordEntity::getTakeInNo, queryVO.getTakeInNo());
        }
        if (StringUtils.isNotBlank(queryVO.getBusinessUser())) {
            queryWrapper.like(TakeInRecordEntity::getBusinessUser, queryVO.getBusinessUser());
        }
        if (StringUtils.isNotBlank(queryVO.getContact())) {
            queryWrapper.like(TakeInRecordEntity::getContact, queryVO.getContact());
        }
        if (StringUtils.isNotBlank(queryVO.getContractNo())) {
            queryWrapper.like(TakeInRecordEntity::getContractNo, queryVO.getContractNo());
        }
        if (StringUtils.isNotBlank(queryVO.getContractCompany())) {
            queryWrapper.like(TakeInRecordEntity::getContractCompany, queryVO.getContractCompany());
        }
        if (Objects.nonNull(queryVO.getTakeInStartTime())) {
            queryWrapper.ge(TakeInRecordEntity::getTakeInTime, queryVO.getTakeInStartTime());
        }
        if (Objects.nonNull(queryVO.getTakeInEndTime())) {
            queryWrapper.le(TakeInRecordEntity::getTakeInTime, queryVO.getTakeInEndTime());
        }
        if (Objects.nonNull(queryVO.getStatus())) {
            queryWrapper.eq(TakeInRecordEntity::getStatus, queryVO.getStatus());
        }
        page = page(page, queryWrapper);
        IPage result = page.convert(e -> {
            TakeInVO record = new TakeInVO();
            BeanUtils.copyProperties(e, record);
            return record;
        });
        return result;
    }


    @Transactional
    @Override
    public void takeInAdd(TakeInVO takeInVO) {
        checkForAdd(takeInVO);
        if (WMSConstants.TAKE_IN_INIT.equals(takeInVO.getStatus())) {
            doSaveTakeIn(takeInVO);
        } else if (WMSConstants.TAKEN_IN.equals(takeInVO.getStatus())) {
            if(Objects.isNull(takeInVO.getTakeInTime())){
                takeInVO.setTakeInTime(LocalDateTime.now());
            }
            doSubmitTakeIn(takeInVO);
        } else {
            throw new BusinessException(ResultCode.PARAM_ERROR, "收货单状态错误");
        }
    }

    private void doSaveTakeIn(TakeInVO takeInVO) {
        if (Objects.isNull(takeInVO.getId())) {
            takeInSave(takeInVO);
        } else {
            takeInUpdate(takeInVO);
        }
    }

    private void takeInSave(TakeInVO takeInVO) {
        //do save
        //goods receive
        TakeInRecordEntity takeInRecordEntity = new TakeInRecordEntity();
        final String takeInNo = SequenceUtil.generateNoByDate("GR");
        takeInVO.setTakeInNo(takeInNo);
        BeanUtils.copyProperties(takeInVO, takeInRecordEntity);
        if (!CollectionUtils.isEmpty(takeInVO.getList())) {
            List<TakeInDetailEntity> prodList = trim2ProdDetailList(takeInVO.getList());
            //计算prodTypes ,totalNumbs,prodTotalPrices,totalFee;
            takeInRecordEntity.setProdTypes(prodList.size());
        }
        save(takeInRecordEntity);
        saveDetails(takeInVO);
    }

    private void doSubmitTakeIn(TakeInVO takeInVO) {
        if (Objects.isNull(takeInVO.getId())) {
            takeInSave(takeInVO);
        } else {
            takeInUpdate(takeInVO);
        }
    }

    private Money totalFee(Money totalPrices, TakeInVO takeInVO) {
        return totalPrices.add(takeInVO.getPileFee()).add(takeInVO.getUnloadFee());
    }

    private Money prodTotalPrices(List<TakeInDetailEntity> prodList) {
        Money prodTotalPrices = prodList
                .stream()
                .reduce(Money.valueOf(BigDecimal.ZERO),
                        (totalPrice, e) -> totalPrice.add(e.getUnitPrice().multiply(e.getProdAmount())), Money::add);
        return prodTotalPrices;
    }

    private Integer totalNumbs(List<TakeInDetailEntity> prodList) {
        return prodList
                .stream()
                .reduce(0, (total, e) -> total + e.getProdAmount(), Integer::sum);
    }

    private List<TakeInDetailEntity> trim2ProdDetailList(List<TakeInProdVO> prodList) {

        //货物整理，按货物编号分类
        Map<String, TakeInDetailEntity> detailMap = new HashMap<>();
        prodList.stream().forEach(prodVO -> {
            if (detailMap.containsKey(prodVO.getProdId())) {
                TakeInDetailEntity takeInDetailEntity = detailMap.get(prodVO.getProdId());
                takeInDetailEntity.setProdAmount(takeInDetailEntity.getProdAmount() + prodVO.getProdAmount());
            } else {
                TakeInDetailEntity takeInDetailEntity = new TakeInDetailEntity();
                BeanUtils.copyProperties(prodVO, takeInDetailEntity);
                detailMap.put(prodVO.getProdId(), takeInDetailEntity);
            }
        });
        return detailMap.values().stream().collect(Collectors.toList());
    }

    private void checkForAdd(TakeInVO takeInVO) {
        LambdaQueryWrapper<LeaseContractEntity> queryCond = new LambdaQueryWrapper<>();
        queryCond.eq(LeaseContractEntity::getContractNo, takeInVO.getContractNo())
                .eq(LeaseContractEntity::getDelFlag, WMSConstants.DEL_FLG_N);
        LeaseContractEntity contract = leaseContractMapper.selectOne(queryCond);

        if (contract == null) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "合同" + takeInVO.getContractNo());
        }
        if (!StringUtils.equals(contract.getStatus(), WMSConstants.CONTRACT_EFFECT)) {
            throw new BusinessException(ResultCode.PARAM_ERROR, "不是有效合同");
        }
    }

    private void saveDetails(TakeInVO takeInVO) {
        if (!CollectionUtils.isEmpty(takeInVO.getList())) {
            List<TakeInDetailEntity> prodEntityList = takeInVO.getList()
                    .stream().map(vo -> {
                        TakeInDetailEntity detailEntity = new TakeInDetailEntity();
                        BeanUtils.copyProperties(vo, detailEntity);
                        detailEntity.setTakeInNo(takeInVO.getTakeInNo());
                        return detailEntity;
                    }).collect(Collectors.toList());
            takeInDetailService.saveBatch(prodEntityList);
        }
    }

    @Override
    public TakeInVO detail(Integer id) {
        TakeInVO result = baseMapper.takeInDetail(id);
        return result;
    }

    @Transactional
    @Override
    public void takeInUpdate(TakeInVO takeInVO) {
        Assert.notNull(takeInVO.getId(), "收货单ID不能为空");
        checkForUpdate(takeInVO);
        TakeInRecordEntity takeInRecordEntity = new TakeInRecordEntity();
        BeanUtils.copyProperties(takeInVO, takeInRecordEntity);
        if (!CollectionUtils.isEmpty(takeInVO.getList())) {
            List<TakeInDetailEntity> prodList = trim2ProdDetailList(takeInVO.getList());
            //计算prodTypes ,totalNumbs,prodTotalPrices,totalFee;
            takeInRecordEntity.setProdTypes(prodList.size());
        }
        updateById(takeInRecordEntity);

        deleteDetails(takeInVO.getTakeInNo());
        saveDetails(takeInVO);
    }

    private void deleteDetails(String takeInNo) {
        LambdaUpdateWrapper<TakeInDetailEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .eq(TakeInDetailEntity::getTakeInNo, takeInNo)
                .set(TakeInDetailEntity::getDelFlag, WMSConstants.DEL_FLG_Y);
        takeInDetailService.remove(updateWrapper);
    }

    private void checkForUpdate(TakeInVO takeInVO) {
        final TakeInRecordEntity takeInRecordEntity = getById(takeInVO.getId());
        if (takeInRecordEntity == null) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "收货单");
        }
        if (StringUtils.equals(takeInRecordEntity.getDelFlag(), WMSConstants.DEL_FLG_Y)) {
            throw new BusinessException(ResultCode.RESOURCE_DELETED, "收货单");
        }
        if (StringUtils.equals(takeInRecordEntity.getStatus(), WMSConstants.TAKEN_IN)) {
            throw new BusinessException(ResultCode.DATA_NO_MODIFY, "收货单已入库");
        }
        if (!StringUtils.equals(takeInRecordEntity.getTakeInNo(), takeInVO.getTakeInNo())) {
            throw new BusinessException(ResultCode.DATA_NO_MODIFY, "收货单号");
        }
    }

    @Override
    public void takenIn(Integer id) {
        TakeInRecordEntity takeInRecord = getById(id);
        Assert.notNull(takeInRecord, "收货单不存在");
        Assert.isTrue(StringUtils.equals(takeInRecord.getDelFlag(), WMSConstants.DEL_FLG_N), "收货单已删除");
        Assert.isTrue(StringUtils.equals(takeInRecord.getStatus(), WMSConstants.TAKE_IN_INIT), "收货单不能重复签收");

        takeInRecord.setStatus(WMSConstants.TAKEN_IN);
        updateById(takeInRecord);

    }
}
