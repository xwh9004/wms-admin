package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.entity.VendorEntity;
import com.wms.admin.mapper.VendorMapper;
import com.wms.admin.service.IVendorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.VendorQueryVO;
import com.wms.admin.vo.VendorVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.wms.admin.commom.WMSConstants.DEL_FLG_N;

/**
 * <p>
 * 供应商表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-13 20:34:22
 */
@Service
public class VendorServiceImpl extends ServiceImpl<VendorMapper, VendorEntity> implements IVendorService {

    @Override
    public List<VendorVO> vendorList() {
        LambdaQueryWrapper<VendorEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(VendorEntity::getDelFlag, DEL_FLG_N);
        queryWrapper.orderByDesc(VendorEntity::getCreateTime);
        queryWrapper.orderByAsc(VendorEntity::getId);
        List<VendorEntity> list = list(queryWrapper);
        List<VendorVO> result = new ArrayList<>();
        if (!list.isEmpty()){
            list.forEach(item->{
                VendorVO vo = new VendorVO();
                BeanUtils.copyProperties(item, vo);
                result.add(vo);
            });
        }
        return result;
    }


    @Override
    public IPage<VendorVO> vendorPages(VendorQueryVO queryVO, PageParam pageParam) {
        IPage<VendorEntity> page = new Page<>(pageParam.getPage(), pageParam.getLimit());
        LambdaQueryWrapper<VendorEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(VendorEntity::getDelFlag, DEL_FLG_N);

        if(StringUtils.isNotBlank(queryVO.getName())){
            queryWrapper.like(VendorEntity::getName,queryVO.getName());
        }
        if(StringUtils.isNotBlank(queryVO.getVendorNo())){
            queryWrapper.like(VendorEntity::getVendorNo,queryVO.getVendorNo());
        }
        if(StringUtils.isNotBlank(queryVO.getType())){
            queryWrapper.eq(VendorEntity::getType,queryVO.getType());
        }
        queryWrapper.orderByDesc(VendorEntity::getCreateTime);
        IPage<VendorVO> resultPage = page(page, queryWrapper).convert(entity -> {
            VendorVO vo = new VendorVO();
            BeanUtils.copyProperties(entity, vo);
            return vo;
        });
        return resultPage;
    }

    @Override
    public void addVendor(VendorVO vendorVO) {
        checkForAdd(vendorVO);
        VendorEntity entity = new VendorEntity();
        entity.setId(UUIDUtil.uuid());
        BeanUtils.copyProperties(vendorVO,entity);
        entity.setCreateBy(UserInfoContext.getUsername());
        entity.setUpdateBy(UserInfoContext.getUsername());
        save(entity);
    }

    private void checkForAdd(VendorVO vendorVO) {
    }

    @Override
    public void updateVendor(VendorVO vendorVO) {
        checkForUpdate(vendorVO);
        VendorEntity vendorEntity = baseMapper.selectById(vendorVO.getId());
        BeanUtils.copyProperties(vendorVO,vendorEntity);
        vendorEntity.setUpdateBy(UserInfoContext.getUsername());
        updateById(vendorEntity);
    }

    private void checkForUpdate(VendorVO vendorVO) {
    }

    @Override
    public void deleteVendor(String vendorId) {
        checkForDelete(vendorId);
        VendorEntity entity = baseMapper.selectById(vendorId);
        entity.setDelFlag(WMSConstants.DEL_FLG_Y);
        entity.setUpdateBy(UserInfoContext.getUsername());
        updateById(entity);
    }


    private void checkForDelete(String vendorId) {
    }
}
