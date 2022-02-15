package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.entity.RegionRacksEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.admin.vo.RegionRackQueryVO;
import com.wms.admin.vo.RegionRackVO;
import com.wms.admin.vo.StoragesRegionVO;

/**
 * <p>
 * 库区货架表 服务类
 * </p>
 *
 * @author Jesse
 * @since 2022-02-13 20:34:22
 */
public interface IRegionRacksService extends IService<RegionRacksEntity> {

    IPage<RegionRackVO> regionRackPages(RegionRackQueryVO queryVO, PageParam pageParam);

    void addRegionRack(RegionRackVO vo);

    void updateRegionRack(RegionRackVO regionRackVO);

    void deleteRegionRack(String rackId);
}
