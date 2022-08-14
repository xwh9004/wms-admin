package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.entity.LesseeAddressEntity;
import com.wms.admin.entity.LesseeInfoEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.LesseeInfoMapper;
import com.wms.admin.service.ILesseeAddressService;
import com.wms.admin.service.ILesseeInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.util.VOUtil;
import com.wms.admin.vo.AddressVO;
import com.wms.admin.vo.LesseeInfoQueryVO;
import com.wms.admin.vo.LesseeInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 承租单位表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-08-01 16:53:12
 */
@Service
public class LesseeInfoServiceImpl extends ServiceImpl<LesseeInfoMapper, LesseeInfoEntity> implements ILesseeInfoService {
    @Autowired
    private ILesseeAddressService lesseeAddressService;

    private static final String IS_DEFAULT = "1";
    private static final String IS_NO_DEFAULT = "0";

    @Override
    public List<LesseeInfoVO> allLessees() {
        return null;
    }

    @Override
    public IPage<LesseeInfoVO> lesseeList(LesseeInfoQueryVO queryVO, PageParam pageParam) {
        IPage<LesseeInfoEntity> page = Page.of(pageParam.getPage(), pageParam.getLimit());
        LambdaQueryWrapper<LesseeInfoEntity> queryCond = new LambdaQueryWrapper<>();
        queryCond.eq(LesseeInfoEntity::getDelFlag,WMSConstants.DEL_FLG_N);
        if (StringUtils.isNotBlank(queryVO.getLesseeNo())) {
            queryCond.like(LesseeInfoEntity::getLesseeNo, queryVO.getLesseeNo());
        }
        if (StringUtils.isNotBlank(queryVO.getContact())) {
            queryCond.like(LesseeInfoEntity::getContact, queryVO.getContact());
        }
        if (StringUtils.isNotBlank(queryVO.getPhone())) {
            queryCond.like(LesseeInfoEntity::getPhone, queryVO.getPhone());
        }
        if (StringUtils.isNotBlank(queryVO.getLesseeCompany())) {
            queryCond.like(LesseeInfoEntity::getLesseeCompany, queryVO.getLesseeCompany());
        }
        page = page(page, queryCond);
        IPage<LesseeInfoVO> result = page.convert(item -> {
            LesseeInfoVO infoVO = new LesseeInfoVO();
            BeanUtils.copyProperties(item, infoVO);
            return infoVO;
        });
        return result;
    }

    @Override
    public LesseeInfoVO detail(Integer id) {
        final LesseeInfoVO detail = baseMapper.lesseeDetail(id);
        if (Objects.isNull(detail)) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "承租单位");
        }
        return detail;
    }

    @Transactional
    @Override
    public void addLessee(LesseeInfoVO infoVO) {
        //新增承租人
        if (lesseeNoExist(infoVO.getLesseeNo(), null)) {
            throw new BusinessException(ResultCode.RESOURCE_EXISTS, "承租编号" + infoVO.getLesseeNo());
        }
        LesseeInfoEntity infoEntity = new LesseeInfoEntity();
        List<AddressVO> addressList = infoVO.getList();
        BeanUtils.copyProperties(infoVO, infoEntity);
        if (!CollectionUtils.isEmpty(addressList)) {
            final AddressVO defaultAddress = setAndGetDefaultAddress(addressList);
            infoEntity.setContact(defaultAddress.getContact());
            infoEntity.setPhone(defaultAddress.getPhone());
            save(infoEntity);
            saveAddresses(infoEntity.getId(), addressList);
        } else {
            save(infoEntity);
        }
    }

    private void saveAddresses(Integer lesseeId, List<AddressVO> addrList) {
        List<LesseeAddressEntity> addressEntities = addrList.stream().map(vo -> {
            LesseeAddressEntity entity = new LesseeAddressEntity();
            BeanUtils.copyProperties(vo, entity);
            entity.setCompanyAddress(vo.getAddress());
            entity.setLesseeId(lesseeId);
            entity.setDelFlag(WMSConstants.DEL_FLG_N);
            if (vo.isDefault()) {
                entity.setIsDefault(IS_DEFAULT);
            } else {
                entity.setIsDefault(IS_NO_DEFAULT);
            }
            return entity;
        }).collect(Collectors.toList());
        lesseeAddressService.saveOrUpdateBatch(addressEntities);
    }

    private AddressVO setAndGetDefaultAddress(List<AddressVO> addrList) {
        AddressVO defaultAddress;
        if (addrList.size() == 1) {
            defaultAddress = addrList.get(0);
        } else {
            Optional<AddressVO> defaultOption = addrList.stream()
                    .filter(vo -> StringUtils.equals(vo.getIsDefault(), "1")).findFirst();
            defaultAddress = defaultOption.orElse(addrList.get(0));
        }
        defaultAddress.setIsDefault(IS_DEFAULT);
        return defaultAddress;
    }

    private boolean lesseeNoExist(String lesseeNo, Integer excludeId) {
        LambdaQueryWrapper<LesseeInfoEntity> queryCond = new LambdaQueryWrapper<>();
        queryCond.eq(LesseeInfoEntity::getLesseeNo, lesseeNo)
                .eq(LesseeInfoEntity::getDelFlag, WMSConstants.DEL_FLG_N);
        if (Objects.nonNull(excludeId)) {
            queryCond.ne(LesseeInfoEntity::getId, excludeId.intValue());
        }
        LesseeInfoEntity lessee = getOne(queryCond);
        return Objects.nonNull(lessee);
    }

    @Transactional
    @Override
    public void updateLessee(LesseeInfoVO infoVO) {
        Assert.notNull(infoVO.getId(), "承租人ID不能为空");
        if (lesseeNoExist(infoVO.getLesseeNo(), infoVO.getId())) {
            throw new BusinessException(ResultCode.RESOURCE_EXISTS, "承租编号" + infoVO.getLesseeNo());
        }
        List<AddressVO> addressList = infoVO.getList();
        LesseeInfoEntity infoEntity = new LesseeInfoEntity();
        BeanUtils.copyProperties(infoVO,infoEntity);
        if (!CollectionUtils.isEmpty(addressList)) {
            final AddressVO defaultAddress = setAndGetDefaultAddress(addressList);
            infoEntity.setContact(defaultAddress.getContact());
            infoEntity.setPhone(defaultAddress.getPhone());
            updateById(infoEntity);
            removeAllAddress(infoEntity.getId());
            saveAddresses(infoEntity.getId(), addressList);
        } else {
            updateById(infoEntity);
            removeAllAddress(infoEntity.getId());
        }
    }


    private void removeAllAddress(Integer lesseeId){
        LesseeAddressEntity addressEntity = new LesseeAddressEntity();
        addressEntity.setLesseeId(lesseeId);
        LambdaUpdateWrapper<LesseeAddressEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .eq(LesseeAddressEntity::getLesseeId, lesseeId)
                .set(LesseeAddressEntity::getDelFlag, WMSConstants.DEL_FLG_Y)
                .set(LesseeAddressEntity::getUpdateBy, UserInfoContext.getUsername())
                .set(LesseeAddressEntity::getUpdateTime, LocalDateTime.now());
        lesseeAddressService.update(addressEntity,updateWrapper);
    }


    @Transactional
    @Override
    public void deleteLessee(Integer id) {
        LesseeInfoEntity infoEntity = new LesseeInfoEntity();
        infoEntity.setDelFlag(WMSConstants.DEL_FLG_Y);
        infoEntity.setId(id);
        updateById(infoEntity);
        removeAllAddress(infoEntity.getId());
    }
}
