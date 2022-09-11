package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.entity.*;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.LeaseContractMapper;
import com.wms.admin.mapper.TakeOutRecordMapper;
import com.wms.admin.service.ITakeOutDetailService;
import com.wms.admin.service.ITakeOutDetailService;
import com.wms.admin.service.ITakeOutRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.util.SequenceUtil;
import com.wms.admin.util.WmsUtils;
import com.wms.admin.vo.*;
import com.wms.admin.vo.TakeOutVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 发货记录表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
@Service
public class TakeOutRecordServiceImpl extends ServiceImpl<TakeOutRecordMapper, TakeOutRecordEntity> implements ITakeOutRecordService {
    @Autowired
    private LeaseContractMapper leaseContractMapper;

    @Autowired
    private ITakeOutDetailService takeOutDetailService;

    @Override
    public IPage<TakeOutVO> takeOutList(TakeOutQueryVO queryVO, PageParam pageParam) {
        WmsUtils.checkDateRange(queryVO.getTakeOutStartTime(), queryVO.getTakeOutEndTime());

        Page page = Page.of(pageParam.getPage(), pageParam.getLimit());

        LambdaQueryWrapper<TakeOutRecordEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(queryVO.getTakeOutNo())) {
            queryWrapper.like(TakeOutRecordEntity::getTakeOutNo, queryVO.getTakeOutNo());
        }
        if (StringUtils.isNotBlank(queryVO.getBusinessUser())) {
            queryWrapper.like(TakeOutRecordEntity::getBusinessUser, queryVO.getBusinessUser());
        }
        if (StringUtils.isNotBlank(queryVO.getContact())) {
            queryWrapper.like(TakeOutRecordEntity::getContact, queryVO.getContact());
        }
        if (StringUtils.isNotBlank(queryVO.getContractNo())) {
            queryWrapper.like(TakeOutRecordEntity::getContractNo, queryVO.getContractNo());
        }
        if (StringUtils.isNotBlank(queryVO.getContractCompany())) {
            queryWrapper.like(TakeOutRecordEntity::getContractCompany, queryVO.getContractCompany());
        }
        if (Objects.nonNull(queryVO.getTakeOutStartTime())) {
            queryWrapper.ge(TakeOutRecordEntity::getTakeOutTime, queryVO.getTakeOutStartTime());
        }
        if (Objects.nonNull(queryVO.getTakeOutEndTime())) {
            queryWrapper.le(TakeOutRecordEntity::getTakeOutTime, queryVO.getTakeOutEndTime());
        }
        if (Objects.nonNull(queryVO.getStatus())) {
            queryWrapper.eq(TakeOutRecordEntity::getStatus, queryVO.getStatus());
        }
        queryWrapper.orderByDesc(TakeOutRecordEntity::getUpdateTime);

        page = page(page, queryWrapper);
        IPage result = page.convert(e -> {
            TakeOutVO record = new TakeOutVO();
            BeanUtils.copyProperties(e, record);
            return record;
        });
        return result;
    }

    @Override
    public void takeOutAdd(TakeOutVO takeOutVO) {
        checkForAdd(takeOutVO);
        if (WMSConstants.TAKE_OUT_INIT.equals(takeOutVO.getStatus())) {
            doSaveTakeOut(takeOutVO);
        } else if (WMSConstants.TAKEN_OUT.equals(takeOutVO.getStatus())) {
            if (Objects.isNull(takeOutVO.getTakeOutTime())) {
                takeOutVO.setTakeOutTime(LocalDateTime.now());
            }
            doSubmitTakeOUt(takeOutVO);
        } else {
            throw new BusinessException(ResultCode.PARAM_ERROR, "出货单状态错误");
        }
    }

    private void doSubmitTakeOUt(TakeOutVO takeOutVO) {
        if (Objects.isNull(takeOutVO.getId())) {
            takeOutSave(takeOutVO);
        } else {
            takeOutUpdate(takeOutVO);
        }
    }

    private void doSaveTakeOut(TakeOutVO takeOutVO) {
        if (Objects.isNull(takeOutVO.getId())) {
            takeOutSave(takeOutVO);
        } else {
            takeOutUpdate(takeOutVO);
        }
    }

    private void takeOutSave(TakeOutVO takeOutVO) {
        //do save
        //goods receive
        TakeOutRecordEntity recordEntity = new TakeOutRecordEntity();
        final String takeOutNo = SequenceUtil.generateNoByDate("GD");
        takeOutVO.setTakeOutNo(takeOutNo);
        BeanUtils.copyProperties(takeOutVO, recordEntity);
        if (!CollectionUtils.isEmpty(takeOutVO.getList())) {
            List<TakeOutDetailEntity> prodList = trim2ProdDetailList(takeOutVO.getList());
            //计算prodTypes ,totalNumbs,prodTotalPrices,totalFee;
            recordEntity.setProdTypes(prodList.size());
        }
        save(recordEntity);
        saveDetails(takeOutVO);
    }

    private void saveDetails(TakeOutVO takeOutVO) {
        if (!CollectionUtils.isEmpty(takeOutVO.getList())) {
            List<TakeOutDetailEntity> prodEntityList = takeOutVO.getList()
                    .stream().map(vo -> {
                        TakeOutDetailEntity detailEntity = new TakeOutDetailEntity();
                        BeanUtils.copyProperties(vo, detailEntity);
                        detailEntity.setTakeOutNo(takeOutVO.getTakeOutNo());
                        return detailEntity;
                    }).collect(Collectors.toList());
            takeOutDetailService.saveBatch(prodEntityList);
        }
    }

    private List<TakeOutDetailEntity> trim2ProdDetailList(List<TakeOutProdVO> list) {
        //货物整理，按货物编号分类
        Map<String, TakeOutDetailEntity> detailMap = new HashMap<>();
        list.stream().forEach(prodVO -> {
            if (detailMap.containsKey(prodVO.getProdId())) {
                TakeOutDetailEntity detailEntity = detailMap.get(prodVO.getProdId());
                detailEntity.setProdAmount(detailEntity.getProdAmount() + prodVO.getProdAmount());
            } else {
                TakeOutDetailEntity detailEntity = new TakeOutDetailEntity();
                BeanUtils.copyProperties(prodVO, detailEntity);
                detailMap.put(prodVO.getProdId(), detailEntity);
            }
        });
        return detailMap.values().stream().collect(Collectors.toList());
    }

    private void checkForAdd(TakeOutVO takeOutVO) {
        LambdaQueryWrapper<LeaseContractEntity> queryCond = new LambdaQueryWrapper<>();
        queryCond.eq(LeaseContractEntity::getContractNo, takeOutVO.getContractNo())
                .eq(LeaseContractEntity::getDelFlag, WMSConstants.DEL_FLG_N);
        LeaseContractEntity contract = leaseContractMapper.selectOne(queryCond);

        if (contract == null) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "合同" + takeOutVO.getContractNo());
        }
        if (!StringUtils.equals(contract.getStatus(), WMSConstants.CONTRACT_EFFECT)) {
            throw new BusinessException(ResultCode.PARAM_ERROR, "不是有效合同");
        }
    }


    @Override
    public void takenOut(Integer id) {
        TakeOutRecordEntity takeOutRecord = getById(id);
        Assert.notNull(takeOutRecord, "发货单不存在");
        Assert.isTrue(StringUtils.equals(takeOutRecord.getDelFlag(), WMSConstants.DEL_FLG_N), "发货单已删除");
        Assert.isTrue(StringUtils.equals(takeOutRecord.getStatus(), WMSConstants.TAKE_IN_INIT), "发货单不能重复签收");

        takeOutRecord.setStatus(WMSConstants.TAKEN_IN);
        updateById(takeOutRecord);
    }

    @Override
    public TakeOutVO detail(Integer id) {
        TakeOutVO result = baseMapper.takeOutDetail(id);
        return result;
    }

    @Override
    public void takeOutUpdate(TakeOutVO takeOutVO) {
        Assert.notNull(takeOutVO.getId(), "发货单ID不能为空");
        checkForUpdate(takeOutVO);
        TakeOutRecordEntity takeOutRecordEntity = new TakeOutRecordEntity();
        BeanUtils.copyProperties(takeOutVO, takeOutRecordEntity);
        if (!CollectionUtils.isEmpty(takeOutVO.getList())) {
            List<TakeOutDetailEntity> prodList = trim2ProdDetailList(takeOutVO.getList());
            //计算prodTypes ,totalNumbs,prodTotalPrices,totalFee;
            takeOutRecordEntity.setProdTypes(prodList.size());
        }
        updateById(takeOutRecordEntity);

        deleteDetails(takeOutVO.getTakeOutNo());
        saveDetails(takeOutVO);
    }

    private void checkForUpdate(TakeOutVO takeOutVO) {
        final TakeOutRecordEntity takeOutRecordEntity = getById(takeOutVO.getId());
        if (takeOutRecordEntity == null) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "发货单");
        }
        if (StringUtils.equals(takeOutRecordEntity.getDelFlag(), WMSConstants.DEL_FLG_Y)) {
            throw new BusinessException(ResultCode.RESOURCE_DELETED, "发货单");
        }
        if (StringUtils.equals(takeOutRecordEntity.getStatus(), WMSConstants.TAKEN_IN)) {
            throw new BusinessException(ResultCode.DATA_NO_MODIFY, "发货单已出库");
        }
        if (!StringUtils.equals(takeOutRecordEntity.getTakeOutNo(), takeOutVO.getTakeOutNo())) {
            throw new BusinessException(ResultCode.DATA_NO_MODIFY, "发货单号");
        }
    }

    private void deleteDetails(String takeOutNo) {
        LambdaUpdateWrapper<TakeOutDetailEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .eq(TakeOutDetailEntity::getTakeOutNo, takeOutNo)
                .set(TakeOutDetailEntity::getDelFlag, WMSConstants.DEL_FLG_Y);
        takeOutDetailService.remove(updateWrapper);
    }
}
