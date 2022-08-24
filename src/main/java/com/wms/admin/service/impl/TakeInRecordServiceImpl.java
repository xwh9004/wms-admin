package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
import com.wms.admin.vo.TakeInProdVO;
import com.wms.admin.vo.TakeInQueryVO;
import com.wms.admin.vo.TakeInVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
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

        return null;
    }

    @Transactional
    @Override
    public void takeInAdd(TakeInVO takeInVO) {
        checkForAdd(takeInVO);
        //goods receive
        final String takeInNo = SequenceUtil.generateNoByDate("GR");
        takeInVO.setTakeInNo(takeInNo);
        TakeInRecordEntity takeInRecordEntity = new TakeInRecordEntity();
        BeanUtils.copyProperties(takeInVO, takeInRecordEntity);
        //计算prodTypes ,totalAmount

        save(takeInRecordEntity);
        saveDetails(takeInVO);
    }

    private void checkForAdd(TakeInVO takeInVO) {
        LambdaQueryWrapper<LeaseContractEntity> queryCond = new LambdaQueryWrapper<>();
        queryCond.eq(LeaseContractEntity::getContractNo,takeInVO.getContractNo())
        .eq(LeaseContractEntity::getDelFlag, WMSConstants.DEL_FLG_N);
        LeaseContractEntity contract = leaseContractMapper.selectOne(queryCond);

        if(contract == null){
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS,"合同"+takeInVO.getContractNo());
        }
        if(!StringUtils.equals(contract.getStatus(),WMSConstants.CONTRACT_EFFECT)){
            throw new BusinessException(ResultCode.PARAM_ERROR,"不是有效合同");
        }
    }

    private void saveDetails(TakeInVO takeInVO) {
        if (!CollectionUtils.isEmpty(takeInVO.getProdList())) {
            List<TakeInDetailEntity> prodEntityList = takeInVO.getProdList()
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
        TakeInRecordEntity takeInRecord = getById(id);
        Assert.notNull(takeInRecord,"收货单不存在");
        Assert.isTrue(StringUtils.equals(takeInRecord.getDelFlag(),WMSConstants.DEL_FLG_N),"收货单已删除");

        LambdaQueryWrapper<TakeInDetailEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TakeInDetailEntity::getTakeInNo,takeInRecord.getTakeInNo())
                .eq(TakeInDetailEntity::getDelFlag,WMSConstants.DEL_FLG_N);
        List<TakeInDetailEntity> detailList = takeInDetailService.list(queryWrapper);
        TakeInVO result = new TakeInVO();
        BeanUtils.copyProperties(takeInRecord,result);
        if(!CollectionUtils.isEmpty(detailList)){
            List<TakeInProdVO> prodList = detailList.stream().map(e -> {
                TakeInProdVO prodVO = new TakeInProdVO();
                BeanUtils.copyProperties(e, prodVO);
                return prodVO;
            }).collect(Collectors.toList());
            result.setProdList(prodList);
        }
        return result;
    }

    @Transactional
    @Override
    public void takeInUpdate(TakeInVO takeInVO) {
        Assert.notNull(takeInVO.getId(),"收货单ID不能为空");
        TakeInRecordEntity takeInRecordEntity = new TakeInRecordEntity();
        BeanUtils.copyProperties(takeInVO, takeInRecordEntity);
        checkForUpdate(takeInVO);
        updateById(takeInRecordEntity);
        deleteDetails(takeInVO.getTakeInNo());
        saveDetails(takeInVO);
    }

    private void deleteDetails(String takeInNo) {
        LambdaUpdateWrapper<TakeInDetailEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .eq(TakeInDetailEntity::getTakeInNo,takeInNo)
                .set(TakeInDetailEntity::getDelFlag,WMSConstants.DEL_FLG_Y);
        takeInDetailService.remove(updateWrapper);
    }

    private void checkForUpdate(TakeInVO takeInVO) {
        final TakeInRecordEntity takeInRecordEntity = getById(takeInVO.getId());
        if(takeInRecordEntity == null){
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS,"收货单");
        }
        if(StringUtils.equals(takeInRecordEntity.getDelFlag(), WMSConstants.DEL_FLG_Y)){
            throw new BusinessException(ResultCode.RESOURCE_DELETED,"收货单");
        }
        if(StringUtils.equals(takeInRecordEntity.getStatus(), WMSConstants.TAKEN_IN)){
            throw new BusinessException(ResultCode.DATA_NO_MODIFY,"收货单已入库");
        }
        if(!StringUtils.equals(takeInRecordEntity.getTakeInNo(), takeInVO.getTakeInNo())){
            throw new BusinessException(ResultCode.DATA_NO_MODIFY,"收货单号");
        }
    }

    @Override
    public void takenIn(Integer id) {
        TakeInRecordEntity takeInRecord = getById(id);
        Assert.notNull(takeInRecord,"收货单不存在");
        Assert.isTrue(StringUtils.equals(takeInRecord.getDelFlag(),WMSConstants.DEL_FLG_N),"收货单已删除");
        Assert.isTrue(StringUtils.equals(takeInRecord.getStatus(),WMSConstants.TAKE_IN_INIT),"收货单不能重复签收");

        takeInRecord.setStatus(WMSConstants.TAKEN_IN);
        updateById(takeInRecord);

    }
}
