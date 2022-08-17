package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.entity.LeaseContractEntity;
import com.wms.admin.entity.LesseeInfoEntity;
import com.wms.admin.mapper.LeaseContractMapper;
import com.wms.admin.mapper.LesseeInfoMapper;
import com.wms.admin.service.ILeaseContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.util.VOUtil;
import com.wms.admin.vo.ContractVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public void contractAll(ContractVO contractVO, PageParam pageParam) {

    }

    @Override
    public void contractList(ContractVO contractVO, PageParam pageParam) {

    }

    @Transactional
    @Override
    public void addContract(ContractVO contractVO) {
        checkForAdd(contractVO);

        LeaseContractEntity contractEntity = VOUtil.toEntity(contractVO, vo -> {
            LeaseContractEntity entity = new LeaseContractEntity();
            BeanUtils.copyProperties(vo, entity);
            return entity;
        });
        save(contractEntity);
    }

    private void checkForAdd(ContractVO contractVO) {
        Assert.isTrue(!existContractNo(contractVO.getLesseeNo()), "合同编号已经存在");
        Assert.isTrue(existLesseeNo(contractVO.getLesseeNo()), "承租单位编号不存在");
        LocalDateTime signDate = contractVO.getSignDate();
        LocalDateTime effectiveDate = contractVO.getEffectiveDate();
        LocalDateTime expireDate = contractVO.getExpireDate();
        Assert.isTrue(!effectiveDate.isAfter(expireDate),"生效时间不能晚于过期时间");
        Assert.isTrue(!signDate.isAfter(effectiveDate),"签约时间不能晚于生效时时间");
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
    public void deleteContract(Integer id) {

    }
}
