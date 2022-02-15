package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.auth.UserInfoContext;
import com.wms.admin.commom.PageParam;
import com.wms.admin.commom.WMSConstants;
import com.wms.admin.entity.ProdCategoryEntity;
import com.wms.admin.entity.ProductEntity;
import com.wms.admin.entity.RegionRacksEntity;
import com.wms.admin.entity.StoragesRegionEntity;
import com.wms.admin.mapper.RegionRacksMapper;
import com.wms.admin.service.IRegionRacksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.util.UUIDUtil;
import com.wms.admin.vo.ProdCategoryVO;
import com.wms.admin.vo.RegionRackQueryVO;
import com.wms.admin.vo.RegionRackVO;
import com.wms.admin.vo.StoragesRegionVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.wms.admin.commom.WMSConstants.DEL_FLG_1;

/**
 * <p>
 * 库区货架表 服务实现类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-13 20:34:22
 */
@Service
public class RegionRacksServiceImpl extends ServiceImpl<RegionRacksMapper, RegionRacksEntity> implements IRegionRacksService {
    @Autowired
    private RegionRacksMapper regionRacksMapper;

    @Override
    public IPage<RegionRackVO> regionRackPages(RegionRackQueryVO queryVO, PageParam pageParam) {
        Page<RegionRackQueryVO> page = new Page<>(pageParam.getPage(), pageParam.getLimit());
        IPage<RegionRackVO> result = regionRacksMapper.rackPage(queryVO,page);
        return result;
    }

    @Override
    public void addRegionRack(RegionRackVO vo) {
        checkForAdd(vo);
        RegionRacksEntity racksEntity = new RegionRacksEntity();
        BeanUtils.copyProperties(vo, racksEntity);
        racksEntity.setId(UUIDUtil.uuid());
        racksEntity.setUsedRacks(0);
        racksEntity.setCreateBy(UserInfoContext.getUsername());
        racksEntity.setUpdateBy(UserInfoContext.getUsername());
        save(racksEntity);
    }

    private void checkForAdd(RegionRackVO vo) {
    }

    @Override
    public void updateRegionRack(RegionRackVO regionRackVO) {
        checkForUpdate(regionRackVO);
        RegionRacksEntity racksEntity = baseMapper.selectById(regionRackVO.getId());
        BeanUtils.copyProperties(regionRackVO, racksEntity);
        racksEntity.setUpdateBy(UserInfoContext.getUsername());
        updateById(racksEntity);
    }

    private void checkForUpdate(RegionRackVO regionRackVO) {
    }

    @Override
    public void deleteRegionRack(String rackId) {
        checkForDelete(rackId);
        RegionRacksEntity entity = baseMapper.selectById(rackId);
        entity.setDelFlag(WMSConstants.DEL_FLG_0);
        entity.setUpdateBy(UserInfoContext.getUsername());
        updateById(entity);
    }

    private void checkForDelete(String rackId) {
    }
}
