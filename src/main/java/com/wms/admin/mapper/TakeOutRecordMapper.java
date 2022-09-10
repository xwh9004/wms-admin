package com.wms.admin.mapper;

import com.wms.admin.entity.TakeOutRecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.admin.vo.TakeOutVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 发货记录表 Mapper 接口
 * </p>
 *
 * @author Jesse
 * @since 2022-08-22 15:02:28
 */
public interface TakeOutRecordMapper extends BaseMapper<TakeOutRecordEntity> {

    TakeOutVO takeOutDetail(@Param("id") Integer id);
}
