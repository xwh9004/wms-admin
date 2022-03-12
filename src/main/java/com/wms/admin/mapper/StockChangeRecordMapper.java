package com.wms.admin.mapper;

import com.wms.admin.entity.StockChangeRecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.admin.vo.StockChangeRecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 产品库存变更记录表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-02-26 14:22:49
 */
public interface StockChangeRecordMapper extends BaseMapper<StockChangeRecordEntity> {
    void saveChangeRecords(@Param("list") List<StockChangeRecordVO> changeRecordVOS);
}
