package com.wms.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.entity.StockMaintainEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.admin.vo.StockMaintainQueryVO;
import com.wms.admin.vo.StockMaintainVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 库存维护记录表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-02-27 20:57:49
 */
public interface StockMaintainMapper extends BaseMapper<StockMaintainEntity> {

    IPage<StockMaintainVO> maintainPages(@Param("param") StockMaintainQueryVO queryVO, Page page);
}
