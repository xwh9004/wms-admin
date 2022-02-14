package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.ResultCode;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.entity.RoleEntity;
import com.wms.admin.entity.StoragesRegionEntity;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.mapper.StoragesRegionMapper;
import com.wms.admin.service.IStoragesRegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.RoleVO;
import com.wms.admin.vo.StoragesRegionQueryVO;
import com.wms.admin.vo.StoragesRegionVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.wms.admin.commom.WMSConstants.DEL_FLG_1;

/**
 * <p>
 * 仓库库区表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-13 20:34:22
 */
@Service
public class StoragesRegionServiceImpl extends ServiceImpl<StoragesRegionMapper, StoragesRegionEntity> implements IStoragesRegionService {

    @Autowired
    StoragesRegionMapper storagesRegionMapper;

    @Override
    public List<StoragesRegionVO> regionList() {
        LambdaQueryWrapper<StoragesRegionEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StoragesRegionEntity::getDelFlag, DEL_FLG_1);
        List<StoragesRegionEntity> list = list(queryWrapper);
        List<StoragesRegionVO> resultList = new ArrayList<>();
        list.forEach(item->{
            StoragesRegionVO vo = new StoragesRegionVO();
            BeanUtils.copyProperties(item,vo);
            resultList.add(vo);
        });
        return resultList;
    }

    @Override
    public IPage<StoragesRegionVO> regionPages(StoragesRegionQueryVO storagesRegionQueryVO, PageParam pageParam) {
        IPage<StoragesRegionEntity> page = new Page<>(pageParam.getPage(), pageParam.getLimit());
        LambdaQueryWrapper<StoragesRegionEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StoragesRegionEntity::getDelFlag, DEL_FLG_1);
        if (StringUtils.isNotBlank(storagesRegionQueryVO.getRegionName())) {
            queryWrapper.like(StoragesRegionEntity::getRegionName, storagesRegionQueryVO.getRegionName());
        }
        if (StringUtils.isNotBlank(storagesRegionQueryVO.getRegionType())) {
            queryWrapper.like(StoragesRegionEntity::getRegionType, storagesRegionQueryVO.getRegionType());
        }
        queryWrapper.orderByDesc(StoragesRegionEntity::getRegionType,StoragesRegionEntity::getCreateTime);
        IPage<StoragesRegionVO> resultPage = page(page, queryWrapper).convert(entity -> {
            StoragesRegionVO storagesRegionVO = new StoragesRegionVO();
            BeanUtils.copyProperties(entity, storagesRegionVO);
            return storagesRegionVO;
        });
        return resultPage;
    }

    @Override
    public void addRegion(StoragesRegionVO storagesRegionVO) {
        checkForAdd(storagesRegionVO);
        StoragesRegionEntity regionEntity = new StoragesRegionEntity();
        BeanUtils.copyProperties(storagesRegionVO, regionEntity);
        regionEntity.setId(UUIDUtil.uuid());
        regionEntity.setCreateBy(UserInfoContext.getUsername());
        regionEntity.setUpdateBy(UserInfoContext.getUsername());
        save(regionEntity);
    }

    @Override
    public void updateRegion(StoragesRegionVO storagesRegionVO) {
        StoragesRegionEntity regionEntity = findExistRegion(storagesRegionVO.getId());
        checkForUpdate(storagesRegionVO);
        BeanUtils.copyProperties(storagesRegionVO, regionEntity, "id");
        regionEntity.setUpdateBy(UserInfoContext.getUsername());
        updateById(regionEntity);
    }

    private void checkForUpdate(StoragesRegionVO storagesRegionVO) {
        LambdaQueryWrapper<StoragesRegionEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StoragesRegionEntity::getRegionNo, storagesRegionVO.getRegionNo())
                .eq(StoragesRegionEntity::getDelFlag, WMSConstants.DEL_FLG_1)
                .ne(StoragesRegionEntity::getId, storagesRegionVO.getId());
        List<StoragesRegionEntity> list = storagesRegionMapper.selectList(queryWrapper);
        if (!list.isEmpty()) {
            throw new BusinessException(ResultCode.RESOURCE_EXISTS, "库区" + storagesRegionVO.getRegionNo());
        }
    }

    @Override
    public void deleteRegion(String id) {
        StoragesRegionEntity regionEntity = findExistRegion(id);
        regionEntity.setDelFlag(WMSConstants.DEL_FLG_0);
        regionEntity.setUpdateBy(UserInfoContext.getUsername());
        storagesRegionMapper.updateById(regionEntity);
    }

    private StoragesRegionEntity findExistRegion(String id) {
        StoragesRegionEntity regionEntity = findById(id);
        if (WMSConstants.DEL_FLG_0.equals(regionEntity.getDelFlag())) {
            throw new BusinessException(ResultCode.RESOURCE_NOT_EXISTS, "库区");
        }
        return regionEntity;
    }

    private StoragesRegionEntity findById(Serializable id) {
        return storagesRegionMapper.selectById(id);
    }

    private void checkForAdd(StoragesRegionVO storagesRegionVO) {
        LambdaQueryWrapper<StoragesRegionEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StoragesRegionEntity::getRegionNo, storagesRegionVO.getRegionNo())
                .eq(StoragesRegionEntity::getDelFlag, WMSConstants.DEL_FLG_1);
        if (!CollectionUtils.isEmpty(storagesRegionMapper.selectList(queryWrapper))) {
            throw new BusinessException(ResultCode.RESOURCE_EXISTS, "库区" + storagesRegionVO.getRegionNo());
        }
    }
}
