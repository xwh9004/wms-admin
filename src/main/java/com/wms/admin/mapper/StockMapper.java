package com.wms.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.entity.StockEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.admin.vo.ProdStockVO;
import com.wms.admin.vo.ProductQueryVO;
import com.wms.admin.vo.ReportQueryVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 库存记录表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-02-26 14:22:49
 */
public interface StockMapper extends BaseMapper<StockEntity> {
    IPage<ProdStockVO> stockPage(@Param(value = "param") ReportQueryVO queryVO, Page page);
}
