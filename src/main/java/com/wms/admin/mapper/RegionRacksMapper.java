package com.wms.admin.mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.entity.RegionRacksEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.admin.vo.RegionRackQueryVO;
import com.wms.admin.vo.RegionRackVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 库区货架表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-02-14 14:43:16
 */
public interface RegionRacksMapper extends BaseMapper<RegionRacksEntity> {

    IPage<RegionRackVO> rackPage(@Param(value = "param") RegionRackQueryVO queryVO, Page<RegionRackQueryVO> page);

}
