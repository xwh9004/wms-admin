package com.wms.admin.mapper;

import com.wms.admin.entity.TakeInRecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.admin.vo.TakeInVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 收货记录表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
public interface TakeInRecordMapper extends BaseMapper<TakeInRecordEntity> {

    TakeInVO takeInDetail(@Param("id") Integer id);

}
