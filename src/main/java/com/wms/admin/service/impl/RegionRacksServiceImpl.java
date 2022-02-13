package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.RegionRacksEntity;
import com.wms.admin.mapper.RegionRacksMapper;
import com.wms.admin.service.IRegionRacksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.admin.vo.RegionRackQueryVO;
import com.wms.admin.vo.RegionRackVO;
import com.wms.admin.vo.StoragesRegionVO;
import org.springframework.stereotype.Service;

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

    @Override
    public IPage<StoragesRegionVO> regionRackPages(RegionRackQueryVO queryVO, PageParam pageParam) {
        return null;
    }

    @Override
    public void addRegionRack(RegionRackVO vo) {

    }

    @Override
    public void updateRegionRack(RegionRackVO regionRackVO) {

    }

    @Override
    public void deleteRegionRack(String rackId) {

    }
}
