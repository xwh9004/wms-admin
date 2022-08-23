package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.entity.ContractProdRelEntity;
import com.wms.admin.entity.LeaseContractEntity;
import com.wms.admin.entity.LesseeInfoEntity;
import com.wms.admin.mapper.ContractProdRelMapper;
import com.wms.admin.mapper.LeaseContractMapper;
import com.wms.admin.mapper.LesseeInfoMapper;
import com.wms.admin.service.IContractProdRelService;
import com.wms.admin.service.ILeaseContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.util.VOUtil;
import com.wms.admin.vo.ContractProdVO;
import com.wms.admin.vo.ContractQueryVO;
import com.wms.admin.vo.ContractVO;
import com.wms.admin.vo.ProductVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 合同表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-08-01 16:53:12
 */
@Service
public class LeaseContractServiceImpl extends ServiceImpl<LeaseContractMapper, LeaseContractEntity> implements ILeaseContractService {

    @Autowired
    private LesseeInfoMapper lesseeInfoMapper;

    @Autowired
    private IContractProdRelService contractProdRelService;

    @Override
    public List<ContractVO> contractAll() {
        LambdaQueryWrapper<LeaseContractEntity> queryCond = new LambdaQueryWrapper();
        queryCond.eq(LeaseContractEntity::getDelFlag, WMSConstants.DEL_FLG_N);
        List<LeaseContractEntity> list = list(queryCond);
        List<ContractVO> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            result = list.stream().map(entity -> VOUtil.toVO(entity, e -> {
                ContractVO vo = new ContractVO();
                BeanUtils.copyProperties(e, vo);
                return vo;
            })).collect(Collectors.toList());
        }
        return result;

    }

    @Override
    public IPage<ContractVO> contractList(ContractQueryVO queryVO, PageParam pageParam) {
        IPage page = Page.of(pageParam.getPage(), pageParam.getLimit());
        LambdaQueryWrapper<LeaseContractEntity> queryCond = new LambdaQueryWrapper();

        if (StringUtils.isNotBlank(queryVO.getContractNo())) {
            queryCond.like(LeaseContractEntity::getContractNo, queryVO.getContractNo());
        }
        if (StringUtils.isNotBlank(queryVO.getLesseeNo())) {
            queryCond.like(LeaseContractEntity::getLesseeNo, queryVO.getLesseeNo());
        }
        if (StringUtils.isNotBlank(queryVO.getLesseePhone())) {
            queryCond.like(LeaseContractEntity::getLesseePhone, queryVO.getLesseePhone());
        }
        if (StringUtils.isNotBlank(queryVO.getBusinessUser())) {
            queryCond.like(LeaseContractEntity::getBusinessUser, queryVO.getBusinessUser());
        }
        if (StringUtils.isNotBlank(queryVO.getBillMethod())) {
            queryCond.eq(LeaseContractEntity::getBillMethod, queryVO.getBillMethod());
        }
        if (StringUtils.isNotBlank(queryVO.getStatus())) {
            queryCond.eq(LeaseContractEntity::getStatus, queryVO.getStatus());
        }
        queryCond.eq(LeaseContractEntity::getDelFlag, WMSConstants.DEL_FLG_N);
        page = page(page, queryCond);
        IPage<ContractVO> resultPage = page.convert(entity -> {
            ContractVO vo = new ContractVO();
            BeanUtils.copyProperties(entity, vo);
            return vo;
        });
        return resultPage;
    }

    @Transactional
    @Override
    public void addContract(ContractVO contractVO) {
        checkForAdd(contractVO);

        LeaseContractEntity contractEntity = VOUtil.toEntity(contractVO, vo -> {
            LeaseContractEntity entity = new LeaseContractEntity();
            entity.setStatus(WMSConstants.CONTRACT_EDITABLE);
            BeanUtils.copyProperties(vo, entity);
            return entity;
        });
        save(contractEntity);
        Integer contractId = contractEntity.getId();

        if (CollectionUtils.isEmpty(contractVO.getList())) {
            saveContractProdInfo(contractId, contractVO.getList());
        }
    }

    private void saveContractProdInfo(final Integer contractId, List<ContractProdVO> list) {
        List<ContractProdRelEntity> entityList = list.stream().map(vo -> {
            ContractProdRelEntity contractProdRelEntity = new ContractProdRelEntity();
            contractProdRelEntity.setContractId(contractId);
            contractProdRelEntity.setProdId(vo.getProdId());
            contractProdRelEntity.setLeaseUnitPrice(vo.getUnitPrice());
            return contractProdRelEntity;
        }).collect(Collectors.toList());
        contractProdRelService.saveBatch(entityList);
    }


    private void checkForAdd(ContractVO contractVO) {
        Assert.isTrue(!existContractNo(contractVO.getLesseeNo()), "合同编号已经存在");
        Assert.isTrue(existLesseeNo(contractVO.getLesseeNo()), "承租单位编号不存在");
        LocalDateTime signDate = contractVO.getSignDate();
        LocalDateTime effectiveDate = contractVO.getEffectiveDate();
        LocalDateTime expireDate = contractVO.getExpireDate();
        Assert.isTrue(!effectiveDate.isAfter(expireDate), "生效时间不能晚于过期时间");
        Assert.isTrue(!signDate.isAfter(effectiveDate), "签约时间不能晚于生效时时间");
    }

    private boolean existContractNo(String contractNo) {
        LambdaQueryWrapper<LeaseContractEntity> queryCond = new LambdaQueryWrapper<>();
        queryCond.eq(LeaseContractEntity::getContractNo, contractNo)
                .eq(LeaseContractEntity::getDelFlag, WMSConstants.DEL_FLG_N);
        return Objects.nonNull(getOne(queryCond));
    }

    private boolean existLesseeNo(String lesseeNo) {
        LambdaQueryWrapper<LesseeInfoEntity> queryCond = new LambdaQueryWrapper<>();
        queryCond.eq(LesseeInfoEntity::getLesseeNo, lesseeNo)
                .eq(LesseeInfoEntity::getDelFlag, WMSConstants.DEL_FLG_N);
        return Objects.nonNull(lesseeInfoMapper.selectOne(queryCond));
    }

    @Override
    public void ineffectiveContract(Integer id) {
        LeaseContractEntity entity = new LeaseContractEntity();
        entity.setId(id);
        entity.setStatus(WMSConstants.CONTRACT_INEFFECTIVE);
        updateById(entity);
    }

    @Transactional
    @Override
    public void deleteContract(Integer id) {
        LeaseContractEntity entity = new LeaseContractEntity();
        entity.setId(id);
        entity.setDelFlag(WMSConstants.DEL_FLG_Y);
        updateById(entity);
        LambdaUpdateWrapper<ContractProdRelEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .eq(ContractProdRelEntity::getDelFlag,WMSConstants.DEL_FLG_N)
                .eq(ContractProdRelEntity::getContractId,id)
                .set(ContractProdRelEntity::getDelFlag,WMSConstants.DEL_FLG_Y);
        contractProdRelService.update(updateWrapper);
    }

    @Override
    public void updateContract(ContractVO contractVO) {
        checkForUpdate(contractVO.getId());
    }

    private void checkForUpdate(Integer id) {

    }
}
