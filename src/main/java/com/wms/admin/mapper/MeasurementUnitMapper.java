package com.wms.admin.mapper;

import com.wms.admin.entity.MeasurementUnitEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 计量单位表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-08-01 16:53:12
 */
public interface MeasurementUnitMapper extends BaseMapper<MeasurementUnitEntity> {

    MeasurementUnitEntity queryUnitSymbol(@Param("symbol") String symbol);
}
